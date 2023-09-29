package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.MauSacModel;
import lombok.Data;

import java.util.Date;

@Data
public class MauSacDTORequest {
    private String ma;
    private String ten;
    private Date ngayTao;
    private Date ngayCapNhat;

    public MauSacModel mapToModel(){
        MauSacModel model = new MauSacModel();
        model.setMa(ma);
        model.setTen(ten);
        model.setNgayTao(ngayTao);
        model.setNgayCapNhat(ngayCapNhat);
        return  model;
    }
}
