package com.example.shoppingweb.dto.reponse;

import com.example.shoppingweb.model.VoucherModel;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VoucherReponse {

    private String ma;

    private String ten;

    private String loai;

    private Double mucGiam;

    private Double giaTriToiThieu;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayBatDau;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayKetThuc;

    private int soLuong;

    private Double mucGiamToiDa;


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
