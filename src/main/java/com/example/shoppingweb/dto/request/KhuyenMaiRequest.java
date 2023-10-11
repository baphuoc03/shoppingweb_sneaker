package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.KhuyenMaiModel;
import com.example.shoppingweb.model.SanPhamModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KhuyenMaiRequest {
    private String ma;

    private String ten;

    private String loai;

    private Double mucGiam;

    private String ngayBatDau;

    private String ngayKetThuc;

    private Boolean trangThai;

    private Date ngayTao;

    private Date ngayCapNhat;

    private List<SanPhamModel> sanPham;

    public KhuyenMaiModel mapToModel() {
        KhuyenMaiModel model = new KhuyenMaiModel();
        model.setMa(ma);
        model.setTen(ten);
        model.setLoai(loai);
        model.setMucGiam(mucGiam);
        model.setNgayBatDau(ngayBatDau);
        model.setNgayKetThuc(ngayKetThuc);
        model.setNgayTao(ngayTao);
        model.setNgayCapNhat(ngayCapNhat);
        model.setSanPham(sanPham);
        return model;
    }

    @Override
    public String toString() {
        return "KhuyenMaiRequest{" +
                "ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                ", loai='" + loai + '\'' +
                ", mucGiam=" + mucGiam +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                ", trangThai=" + trangThai +
                ", ngayTao=" + ngayTao +
                ", ngayCapNhat=" + ngayCapNhat +
                ", sanPham=" +
                sanPham.stream().map(s -> s.getMa() + " - " +s.getTen()).collect(Collectors.joining());
    }
}
