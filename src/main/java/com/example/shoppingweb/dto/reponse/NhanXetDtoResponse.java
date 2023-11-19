package com.example.shoppingweb.dto.reponse;

import com.example.shoppingweb.model.NhanXetModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NhanXetDtoResponse {
    private String id;
    private KhachHangDtoResponse khachHang;
    private SanPhamDtoResponse sanPham;
    private Float rating;
    private String tieuDe;
    private String noiDung;
    private Date thoiGian;

    public NhanXetDtoResponse(NhanXetModel model) {
        this.id = model.getId();
        this.khachHang = new KhachHangDtoResponse(model.getKhachHang());
        this.sanPham = new SanPhamDtoResponse(model.getSanPham());
        this.rating = model.getRating();
        this.tieuDe = model.getTieuDe();
        this.noiDung = model.getNoiDung();
        this.thoiGian = model.getThoiGian();
    }
}
