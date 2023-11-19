package com.example.shoppingweb.restcontroller.admin;

import com.example.shoppingweb.dto.reponse.DonHangDtoResponse;
import com.example.shoppingweb.dto.request.ChiTietDonHangDTORequest;
import com.example.shoppingweb.dto.request.DonHangDTORequest;
import com.example.shoppingweb.service.IDonHangService;
import com.example.shoppingweb.util.ValidateUtil;
import jakarta.mail.MessagingException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Map;

@RestController("don-hang-restCtrl-admin")
@RequestMapping("${admin.domain}/don-hang")
public class DonHangRescontroller {
    @Autowired
    private IDonHangService donHangService;

    @GetMapping("get-by-trangthai")
    public Page<DonHangDtoResponse> getChuaXacNhan(@RequestParam("trangThai") Integer trangThai,
                                                   @RequestParam(defaultValue = "0") Integer pageNumber,
                                                   @RequestParam(defaultValue = "10") Integer limit) {
        return donHangService.getAllByTrangThai(trangThai, limit, pageNumber);
    }



    @GetMapping("/{ma}")
    public ResponseEntity<DonHangDtoResponse> getByMa(@PathVariable("ma") String ma) {
        if (!donHangService.existsByMa(ma)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(donHangService.findByMa(ma));
    }

    @GetMapping("update-trang-thai/{ma}")
    public ResponseEntity<Integer> updatTrangThai(@PathVariable("ma") String ma, @RequestParam("trangThai") Integer trangThai) throws MessagingException {
        if (!donHangService.existsByMa(ma)) {
            return ResponseEntity.notFound().build();
        }
        donHangService.updateTrangThai(ma, trangThai);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update-trang-thai")
    public ResponseEntity<Integer> updatTrangThaiAll(@RequestBody List<String> ma, @RequestParam("trangThai") Integer trangThai) throws MessagingException {
        ma.forEach(m -> {
            try {
                donHangService.updateTrangThai(m, trangThai);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        return ResponseEntity.ok().build();
    }

    @PutMapping("/huy-don-hang")
    public ResponseEntity<Integer> huyDonHang(@RequestBody List<String> ma, @RequestParam("lyDo") String lyDo) throws MessagingException {
        donHangService.huyDonHang(ma, lyDo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ResponseEntity<?> updateDonHang(@Valid @RequestPart("donHang") DonHangDTORequest request,
                                           BindingResult result,
                                           @RequestPart("chiTietDonHang") List<ChiTietDonHangDTORequest> products) {
        if(products.size()<=0){
            result.addError(new FieldError("soLuongSP","soLuongSP","Không có sản phẩm trong đơn hàng"));
        }

        if(result.hasErrors()){
            return ValidateUtil.getErrors(result);
        }
        if (!donHangService.existsByMa(request.getMa())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(donHangService.updateDonHang(request, products));
    }


}
