package com.example.shoppingweb.dto.thongKe;

import com.example.shoppingweb.dto.reponse.SanPhamDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SanPhamBanChayDto {
    private SanPhamDtoResponse sanPham;
    private Long soLuong;
}
