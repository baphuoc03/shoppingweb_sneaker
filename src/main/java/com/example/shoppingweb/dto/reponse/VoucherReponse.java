package com.example.shoppingweb.dto.reponse;

import com.example.shoppingweb.model.VoucherModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VoucherReponse {

    private String ma;

    private String ten;

    private String loai;

    private Double mucGiam;

    private BigDecimal giaTriToiThieu;

    private Date ngayBatDau;

    private Date ngayKetThuc;

    private Long soLuong;

    private Long mucGiamToiDa;


    public VoucherReponse(VoucherModel model) {
        this.ma = model.getMa();
        this.ten = model.getTen();
        this.loai = model.getLoai();
        this.mucGiam = model.getMucGiam();
        this.giaTriToiThieu = model.getGiaTriToiThieu();
        this.ngayBatDau = model.getNgayBatDau();
        this.ngayKetThuc = model.getNgayKetThuc();
        this.soLuong = model.getSoLuong();
        this.mucGiamToiDa = model.getMucGiamToiDa();
    }
}
