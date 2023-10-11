package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.KieuDangModel;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KieuDangDtoRequest {
    private String id;
    @NotBlank(message = "vui long nhap ten")
    private String ten;
    private Date ngayTao;
    private Date ngayCapNhat;

    public KieuDangModel mapToModel() {
        KieuDangModel model = new KieuDangModel();
        model.setId(id);
        model.setTen(ten);
        model.setNgayTao(ngayTao);
        model.setNgayCapNhat(ngayCapNhat);
        return model;
    }
}
