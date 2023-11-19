package com.example.shoppingweb.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@ToString
@Table(name = "sanpham")
public class SanPhamModel {
    @Id
    @Column(name = "ma")
    private String ma;

    @ManyToOne
    @JoinColumn(name = "mausac")
    private MauSacModel mauSac;

    @ManyToOne
    @JoinColumn(name = "dongsp")
    private DongSanPhamModel dongSanPham;

    @ManyToOne
    @JoinColumn(name = "xuatxu")
    private XuatXuModel xuatXu;

    @ManyToOne
    @JoinColumn(name = "kieudang")
    private KieuDangModel kieuDang;

    @ManyToOne
    @JoinColumn(name = "chatlieu")
    private ChatLieuModel chatLieu;

    @Column(name = "ten")
    private String ten;

    @Column(name = "gianhap")
    private BigDecimal giaNiemYet;

    @Column(name = "giaban")
    private BigDecimal giaBan;

    @Column(name = "mota")
    private String moTa;

    @Column(name = "ngaytao")
    @CreationTimestamp
    private Date ngayTao;

    @Column(name = "ngaycapnhat")
    @UpdateTimestamp
    private Date ngayCapNhat;

    @Column(name = "hienthi")
    private Boolean hienThi;

    @Column(name = "trangthai")
    private Boolean trangThai;

//    @Transient
    @Formula("(SELECT SUM(c.soluong) FROM chitietsanpham c WHERE c.sanpham = ma)")
    private Long soLuong;

    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<AnhModel> Images;

    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<NhanXetModel> nhanXet;

    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<ChiTietSanPhamModel> ctsp;

    public Long getSoLuongSanPham() {
        if (ctsp == null) return 0L;
        return ctsp.stream().filter(c -> c.getTrangThai()==true).map(c -> c.getSoLuong()).reduce(0L, (c1, c2) -> c1 + c2);
    }

//    public Long getSoLuong() {
//        if (ctsp == null) return 0L;
//        return ctsp.stream().filter(c -> c.getTrangThai()==true).map(c -> c.getSoLuong()).reduce(0L, (c1, c2) -> c1 + c2);
//
//    }

    @Override
    public String toString() {
        return "SanPhamModel{" +
                "ma='" + ma + '\'' +
                ", mauSac=" + mauSac.getTen() +
                ", ten='" + ten + '\'' +
                ", giaNhap=" + giaNiemYet +
                ", giaBan=" + giaBan +
                ", moTa='" + moTa + '\'' +
                ", ngayTao=" + ngayTao +
                ", ngayCapNhat=" + ngayCapNhat +
                ", hienThi=" + hienThi +
                ", trangThai=" + trangThai +
                ", nhanXet=" + nhanXet +
                ", ctsp=" + ctsp +
                '}';
    }

    public SanPhamModel(String ma){ this.ma = ma;}
}
