package com.example.shoppingweb.dto.reponse;

import com.example.shoppingweb.model.KhachHangModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhachHangDtoResponse {

    private String username;
    private String password;
    private String hoVaTen;
    private String gioiTinh;
    private LocalDate ngaySinh;
    private String soDienThoai;
    private String email;
    private String anhDaiDien;

    public KhachHangDtoResponse(KhachHangModel model) {
        this.username = model.getUsername();
        this.password = model.getPassword();
        this.hoVaTen = model.getHoVaTen();
        if(gioiTinh != null && gioiTinh.isBlank()){
            this.gioiTinh = model.getGioiTinh() == true ? "Nam" : "Nữ";
        }
        this.ngaySinh = model.getNgaySinh();
        this.soDienThoai = model.getSoDienThoai();
        this.email = model.getEmail();
        this.anhDaiDien = model.getAnhDaiDien();
    }
}
