package com.example.shoppingweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "khuyenmai")
public class KhuyenMaiModel {
    @Id
    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "loai")
    private String loai;

    @Column(name = "mucgiam")
    private Double mucGiam;
    @Column(name = "ngaybatdau")
    private String ngayBatDau;

    @Column(name = "ngayketthuc")
    private String ngayKetThuc;

    @Column(name = "ngaytao")
    private Date ngayTao;

    @Column(name = "ngaycapnhat")
    private Date ngayCapNhat;
    @ManyToMany
    @JoinTable(name = "khuyenmai_sanpham",
            joinColumns = {@JoinColumn(name = "khuyenmai")},
            inverseJoinColumns = {@JoinColumn(name = "sanpham")})
    private List<SanPhamModel> sanPham;

}
