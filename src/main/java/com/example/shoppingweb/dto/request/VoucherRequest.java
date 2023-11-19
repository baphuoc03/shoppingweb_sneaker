package com.example.shoppingweb.dto.request;


import com.example.shoppingweb.model.VoucherModel;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VoucherRequest {
    private String ma;

    @NotEmpty(message = "Vui lòng nhập dữ liệu")
    private String ten;

    private String loai;

    @NotNull(message = "Vui lòng nhập dữ liệu")
    private Double mucGiam;

    @NotNull(message = "Vui lòng nhập dữ liệu")
    private Double giaTriToiThieu;

    @NotNull(message = "Vui lòng nhập dữ liệu")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayBatDau;

    @NotNull(message = "Vui lòng nhập dữ liệu")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayKetThuc;

    @NotNull(message = "Vui lòng nhập dữ liệu")
    private int soLuong;

    private Double mucGiamToiDa;


    public VoucherModel maptoModel() {
        VoucherModel model = new VoucherModel();
        model.setMa(ma);
        model.setTen(ten);
        model.setLoai(loai);
        model.setMucGiam(mucGiam);
        model.setGiaTriToiThieu(giaTriToiThieu);
        model.setNgayBatDau(ngayBatDau);
        model.setNgayKetThuc(ngayKetThuc);
        model.setSoLuong(soLuong);
        model.setMucGiamToiDa(mucGiamToiDa);
        return model;
    }
}
