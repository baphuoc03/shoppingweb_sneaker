package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.XuatXuModel;
import lombok.Data;

import java.util.Date;

@Data
public class XuatXuRequest {
    private String id;
    private String ten;
    private Date ngayTao;
    private Date ngayCapNhat;

    public XuatXuModel mapXuatXuToModel(){
        XuatXuModel model = new XuatXuModel();
        model.setId(id);
        model.setTen(ten);
        model.setNgayTao(ngayTao);
        model.setNgayCapNhat(ngayCapNhat);
        return model;
    }
}
