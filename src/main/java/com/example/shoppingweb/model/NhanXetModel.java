package com.example.shoppingweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "nhanxet")
public class NhanXetModel {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "Khachhang")
    private KhachHangModel khachHang;

    @ManyToOne
    @JoinColumn(name = "sanpham")
    private SanPhamModel sanPham;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "tieude")
    private String tieuDe;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "thoigian")
    private Date thoiGian;

}
