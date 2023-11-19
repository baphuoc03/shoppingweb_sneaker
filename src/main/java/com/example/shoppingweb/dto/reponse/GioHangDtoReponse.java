package com.example.shoppingweb.dto.reponse;

import com.example.shoppingweb.model.ChiTietSanPhamModel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GioHangDtoReponse {
    private String id;
    private String anh;
    private BigDecimal donGia;
    private BigDecimal donGiaSauGiam;
    private String tenSanPham;
    private String maSanPham;
    private Float size;
    private Integer soLuong;

    public GioHangDtoReponse(ChiTietSanPhamModel model, Integer sl) {
        id = model.getId();
        tenSanPham = model.getSanPham() == null ? "" : model.getSanPham().getTen();
        maSanPham = model.getSanPham() == null ? "" : model.getSanPham().getMa();
        donGia = model.getSanPham().getGiaBan();
        donGiaSauGiam = model.getSanPham().getGiaNiemYet();
        size = model.getSize().getMa();
        anh = model.getSanPham().getImages().size() == 0 ? "default.png" : model.getSanPham().getImages().get(0).getTen();
//        soLuong = model.getSoLuong();
        soLuong = sl;
    }

}
