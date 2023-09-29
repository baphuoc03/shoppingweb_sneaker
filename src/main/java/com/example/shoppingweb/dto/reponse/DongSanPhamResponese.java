package com.example.shoppingweb.dto.reponse;

import com.example.shoppingweb.model.DongSanPhamModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DongSanPhamResponese {
    private String id;
    private String ten;
    private Date ngayTao;
    private Date ngayCapNhat;

    public DongSanPhamResponese(DongSanPhamModel model){
        id = model.getId();
        ten = model.getTen();
        ngayTao = model.getNgayTao();
        ngayCapNhat = model.getNgayCapNhat();
    }
}
