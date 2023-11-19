package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.DonHangModel;
import com.example.shoppingweb.model.KhachHangModel;
import com.example.shoppingweb.model.VoucherModel;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DonHangDTORequest {
    private String ma;
    private KhachHangModel nguoiSoHuu;
    private String voucher;
    @NotBlank(message = "Vui lòng nhập dữ liệu")
    private String tenNguoiNhan;
    @NotBlank(message = "Vui lòng nhập dữ liệu")
    @Pattern(regexp = "0\\d{9}", message = "Số điện thoại không đúng định dạng")
    private String soDienThoai;
    @NotBlank(message = "Vui lòng nhập dữ liệu")
    @Email(message = "Email không đúng định dạng")
    private String email;
    @NotBlank(message = "Vui lòng nhập dữ liệu")
    private String thanhPhoName;
    @NotNull(message = "Vui lòng nhập dữ liệu")
    private Integer thanhPhoCode;
    @NotBlank(message = "Vui lòng nhập dữ liệu")
    private String quanHuyenName;
    @NotNull(message = "Vui lòng nhập dữ liệu")
    private Integer quanHuyenCode;
    @NotBlank(message = "Vui lòng nhập dữ liệu")
    private String xaPhuongName;
    @NotBlank(message = "Vui lòng nhập dữ liệu")
    private String xaPhuongCode;
    @NotBlank(message = "Vui lòng nhập dữ liệu")
    private String diaChiChiTiet;
    private Date ngayDatHang;
    private Integer trangThai;
    private String ghiChu;
    private BigDecimal tienGiam;
    private BigDecimal phiGiaoHang;
    @NotNull(message = "Vui lòng chọn hình thức thanh toán")
    private int phuongThucThanhToan;
    private String trangThaiDetail;
//    @NotBlank(message = "Nhập lý do hủy đơn")
    private String lyDoHuy;

    public DonHangModel mapModel() {
        DonHangModel donHangModel = new DonHangModel();
        if (this.voucher != null && this.voucher.length() > 0) {
            VoucherModel voucherModel = new VoucherModel();
            voucherModel.setMa(voucher);
            donHangModel.setVoucher(voucherModel);
        }
        donHangModel.setMa(ma);
        if (nguoiSoHuu != null) donHangModel.setNguoiSoHuu(nguoiSoHuu);
        if (lyDoHuy != null && !lyDoHuy.isBlank()) donHangModel.setLyDoHuy(lyDoHuy);
        donHangModel.setTenNguoiNhan(tenNguoiNhan);
        donHangModel.setSoDienThoai(soDienThoai);
        donHangModel.setEmail(email);
        donHangModel.setThanhphoName(thanhPhoName);
        donHangModel.setThanhPhoCode(thanhPhoCode);
        donHangModel.setQuanHuyenName(quanHuyenName);
        donHangModel.setQuanHuyenCode(quanHuyenCode);
        donHangModel.setXaPhuongName(xaPhuongName);
        donHangModel.setXaPhuongCode(xaPhuongCode);
        donHangModel.setDiaChiChiTiet(diaChiChiTiet);
        donHangModel.setNgayDatHang(ngayDatHang);
        donHangModel.setTrangThai(2);
        donHangModel.setGhiChu(ghiChu);
        donHangModel.setTienGiam(tienGiam);
        donHangModel.setPhiGiaoHang(phiGiaoHang);
        donHangModel.setPhuongThucThanhToan(phuongThucThanhToan == 1 ? false : true);
        return donHangModel;
    }
}
