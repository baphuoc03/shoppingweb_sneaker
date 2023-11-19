package com.example.shoppingweb.model;
import fpoly.duantotnghiep.shoppingweb.enumtype.KhuyenMaiType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "voucher")
public class VoucherModel {
    @Id
    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "loaivoucher")
    private String loai;

    @Column(name = "mucgiam")
    private Double mucGiam;

    @Column(name = "giatritoithieu")
    private Double giaTriToiThieu;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngaybatdau")
    private Date ngayBatDau;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngayketthuc")
    private Date ngayKetThuc;

    @Column(name = "soluong")
    private int soLuong;

    @Column(name = "mucgiamtoida")
    private Double mucGiamToiDa;

}
