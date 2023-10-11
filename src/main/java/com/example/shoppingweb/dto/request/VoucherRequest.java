package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.VoucherModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VoucherRequest {
    private String ma;

    private String ten;

    private String loai;

    private Double mucGiam;

    private BigDecimal giaTriToiThieu;

    private Date ngayBatDau;

    private Date ngayKetThuc;

    private Long soLuong;

    private Long mucGiamToiDa;


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
