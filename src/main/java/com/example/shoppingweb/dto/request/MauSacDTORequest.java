package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.MauSacModel;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MauSacDTORequest {
    private String ma;
    @NotBlank(message = "Không được để trống tên!!!")
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
