package com.example.shoppingweb.dto.reponse;

import com.example.shoppingweb.model.ChiTietSanPhamModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChiTietSanPhamDtoResponse {

    private String id;
    private String sanPham;
    private Float size;
    private Long soLuong;

    public ChiTietSanPhamDtoResponse(ChiTietSanPhamModel model) {
        id = model.getId();
        sanPham = model.getSanPham().getTen();
        size = model.getSize().getMa();
        soLuong = model.getSoLuong();
    }
}
