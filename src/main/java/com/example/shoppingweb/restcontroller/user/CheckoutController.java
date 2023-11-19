package com.example.shoppingweb.restcontroller.user;

import com.example.shoppingweb.dto.reponse.ChiTietSanPhamDtoResponse;
import com.example.shoppingweb.dto.reponse.DonHangDtoResponse;
import com.example.shoppingweb.dto.reponse.KhachHangDtoResponse;
import com.example.shoppingweb.dto.reponse.VoucherReponse;
import com.example.shoppingweb.dto.request.ChiTietDonHangDTORequest;
import com.example.shoppingweb.dto.request.DonHangDTORequest;
import com.example.shoppingweb.model.KhachHangModel;
import com.example.shoppingweb.service.IChiTietDonHangService;
import com.example.shoppingweb.service.IChiTietSanPhamService;
import com.example.shoppingweb.service.IDonHangService;
import com.example.shoppingweb.service.IKhachHangService;
import com.example.shoppingweb.service.impl.GioHangServiceImpl;
import com.example.shoppingweb.service.impl.VoucherServiceImpl;
import com.example.shoppingweb.util.ValidateUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class CheckoutController {
    @Autowired
    IKhachHangService khachHangService;
    @Autowired
    GioHangServiceImpl gioHangService;
    @Autowired
    IChiTietDonHangService chiTietDonHangService;
    @Autowired
    IDonHangService donHangService;
    @Autowired
    IChiTietSanPhamService sanPhamServic;
    @Autowired
    VoucherServiceImpl voucherService;

    @GetMapping("/check-out/voucher")
    public ResponseEntity<List<VoucherReponse>> checkOutVoucher() {
        return ResponseEntity.ok(voucherService.voucherEligible());
    }

    @GetMapping("get-khach-hang-thanh-toan")
    public ResponseEntity<KhachHangDtoResponse> getKhachHangThanhToan(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.ok(new KhachHangDtoResponse());
        }
        String userName = authentication.getName();
        return ResponseEntity.ok(khachHangService.findById(userName));
    }

    @PostMapping("/check-out/disable-voucher")
    public ResponseEntity<List<VoucherReponse>> disabledVoucher(@RequestBody Map<String, Double> request) {
        Double giaTri = Double.parseDouble(request.get("tienHang").toString());
        return ResponseEntity.ok(voucherService.disabledVoucher(giaTri));
    }

    @PostMapping("/check-out")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})//Khi có lỗi sẽ rollback
    public ResponseEntity<?> addHoaDon(@Valid @RequestBody DonHangDTORequest donHangDTORequest,
                                       BindingResult result,
                                       Authentication authentication) throws MessagingException {

        if (result.hasErrors()) {
            return ValidateUtil.getErrors(result);
        }
        if (authentication != null) {
            String khachHang = authentication.getName();
            KhachHangModel khachHangModel = new KhachHangModel();
            khachHangModel.setUsername(khachHang);
            donHangDTORequest.setNguoiSoHuu(khachHangModel);
        }
        donHangDTORequest.setNgayDatHang(new Date());
        donHangDTORequest.setMa(codeDonHang());
        DonHangDtoResponse response = donHangService.checkOut(donHangDTORequest);
//        save chi tiết đơn hàng
        gioHangService.laySpTrongGio().stream().forEach(c -> {
            ChiTietDonHangDTORequest donHangCT = new ChiTietDonHangDTORequest(response.getMa(), c.getId(), c.getSoLuong(), c.getDonGia(), c.getDonGiaSauGiam());
            chiTietDonHangService.save(donHangCT);
            ChiTietSanPhamDtoResponse chtsp = sanPhamServic.finById(c.getId());
            Long sl = chtsp.getSoLuong() - c.getSoLuong();
            sanPhamServic.updateSL(chtsp.getId(), sl);
        });
//        update cart and soluong voucher
        if (donHangDTORequest.getVoucher() != null && !donHangDTORequest.getVoucher().isBlank()) {
            int soLuong = voucherService.findById(donHangDTORequest.getVoucher()).getSoLuong() - 1;
            voucherService.upddateSoLuong(soLuong, donHangDTORequest.getVoucher());
        }
        gioHangService.removeAllProdcutInCart();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/using-voucher")
    public Double giaGiam(@RequestBody Map<String, Object> request) {
        VoucherReponse voucherResponse = voucherService.findById(request.get("voucher").toString());
        Double tongThanhToan = Double.parseDouble(request.get("tongThanhToan").toString());
        Double giaGiam = null;
        if (voucherResponse != null) {
            giaGiam = voucherResponse.getMucGiam();
            if (voucherResponse.getLoai().equals("PHAN TRAM")) {
                giaGiam = tongThanhToan * (voucherResponse.getMucGiam()) / 100;
                if (giaGiam > voucherResponse.getMucGiamToiDa()) {
                    giaGiam = voucherResponse.getMucGiamToiDa();
                }
            }
        }
        return giaGiam;
    }


    private String codeDonHang() {
        final String ALLOWED_CHARACTERS = "asdfghjklqwertyuiopzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        final int CODE_LENGTH = 8;

        StringBuilder code = new StringBuilder();

        Random random = new Random();
        int maxIndex = ALLOWED_CHARACTERS.length();

        // Sinh ngẫu nhiên các ký tự cho mã
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(maxIndex);
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            code.append(randomChar);
        }

        return code.toString();
    }

}
