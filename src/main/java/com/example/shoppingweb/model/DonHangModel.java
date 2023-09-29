package com.example.shoppingweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "donhang")
public class DonHangModel {

    @Id
    @Column(name = "ma")
    private String ma;

    @ManyToOne
    @JoinColumn(name = "Khachhang")
    private KhachHangModel nguoiSoHuu;

    @Column(name = "tennguoinhan")
    private String tenNguoiNhan;

    @Column(name = "sodienthoai")
    private String soDienThoai;

    @Column(name = "thanhpho")
    private Integer thanhPho;

    @Column(name = "quanhuyen")
    private Integer quanHuyen;

    @Column(name = "xaphuong")
    private String xaPhuong;

    @Column(name = "diachichitiet")
    private String diaChiChiTiet;

    @Column(name = "ngaydathang")
    private Date ngayDatHang;

    @Column(name = "trangthai")
    private String trangThai;

    @Column(name = "ghichu")
    private String ghiChu;

    @Column(name = "tiengiam")
    private BigDecimal tienGiam;

    @Column(name = "phigiaohang")
    private BigDecimal phiGiaoHang;

    @OneToMany(mappedBy = "donHang",fetch = FetchType.LAZY)
    private List<ChiTietDonHangModel> danhSachSanPham;

}
