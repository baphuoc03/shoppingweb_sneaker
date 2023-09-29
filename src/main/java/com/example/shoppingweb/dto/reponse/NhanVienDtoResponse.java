package com.example.shoppingweb.dto.reponse;

import com.example.shoppingweb.model.NhanVienModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVienDtoResponse {

    private String vaiTro;
    private String username;
    private String hoVaTen;
    private String gioiTinh;
    private LocalDate ngaySinh;
    private String soDienThoai;
    private String email;
    private String anhDaiDien;

    public NhanVienDtoResponse(NhanVienModel model) {
        this.vaiTro = model.getVaiTro().getTen();
        this.username = model.getUsername();
        this.hoVaTen = model.getHoVaTen();
        this.gioiTinh = model.getGioiTinh() == true ? "Nam" : "Nữ";
        this.ngaySinh = model.getNgaySinh();
        this.soDienThoai = model.getSoDienThoai();
        this.email = model.getEmail();
        this.anhDaiDien = model.getAnhDaiDien();
    }
}
