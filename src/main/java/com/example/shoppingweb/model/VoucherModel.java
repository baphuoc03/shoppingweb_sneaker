package com.example.shoppingweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private BigDecimal giaTriToiThieu;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngaybatdau")
    private Date ngayBatDau;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngayketthuc")
    private Date ngayKetThuc;

    @Column(name = "soluong")
    private Long soLuong;

    @Column(name = "mucgiamtoida")
    private Long mucGiamToiDa;

}
