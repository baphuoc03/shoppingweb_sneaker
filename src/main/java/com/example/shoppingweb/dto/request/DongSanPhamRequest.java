package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.DongSanPhamModel;
import lombok.Data;

import java.util.Date;

@Data
public class DongSanPhamRequest {
    private String id;
    private String ten;
    private Date ngayTao;
    private Date ngayCapNhat;

    public DongSanPhamModel maptomodel(){
        DongSanPhamModel model = new DongSanPhamModel();
        model.setId(id);
        model.setTen(ten);
        model.setNgayTao(ngayTao);
        model.setNgayCapNhat(ngayCapNhat);
        return model;
    }
}
