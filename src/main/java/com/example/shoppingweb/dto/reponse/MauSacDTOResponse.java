package com.example.shoppingweb.dto.reponse;

import com.example.shoppingweb.model.MauSacModel;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ui.Model;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MauSacDTOResponse {
    private String ma;
    private String ten;
    private Date ngayTao;
    private Date ngayCapNhat;

    public MauSacDTOResponse(MauSacModel model){
        ma = model.getMa();
        ten = model.getTen();
        ngayTao = model.getNgayTao();
        ngayCapNhat = model.getNgayCapNhat();

    }
}
