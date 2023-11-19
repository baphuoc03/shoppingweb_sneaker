package com.example.shoppingweb.dto.request;


import com.example.shoppingweb.model.ThuongHieuModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ThuongHieuDtoRequest {
    private String id;
    private String ten;
    private Date ngayTao;
    private Date ngayCapNhat;

    public ThuongHieuModel mapToModel(){
        ThuongHieuModel model = new ThuongHieuModel();
        model.setId(id);
        model.setTen(ten);
        model.setNgayTao(ngayTao);
        model.setNgayCapNhat(ngayCapNhat);
        return  model;
    }
}
