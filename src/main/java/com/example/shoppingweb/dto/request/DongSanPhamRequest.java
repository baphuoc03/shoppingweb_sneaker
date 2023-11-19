package com.example.shoppingweb.dto.request;


import com.example.shoppingweb.model.DongSanPhamModel;
import com.example.shoppingweb.model.ThuongHieuModel;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DongSanPhamRequest {
    private String id;
    @NotBlank(message = "Không được để trống tên!!")
    private String ten;
    private String thuongHieu;
    private Date ngayTao;
    private Date ngayCapNhat;

    public DongSanPhamModel maptomodel(){
        DongSanPhamModel model = new DongSanPhamModel();
        model.setId(id);
        model.setTen(ten);
        if(thuongHieu != null && !thuongHieu.isBlank()) model.setThuongHieu(new ThuongHieuModel(thuongHieu));
        model.setNgayTao(ngayTao);
        model.setNgayCapNhat(ngayCapNhat);
        return model;
    }
}
