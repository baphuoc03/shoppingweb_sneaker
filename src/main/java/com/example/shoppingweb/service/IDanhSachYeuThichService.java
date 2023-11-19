package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.DanhSachYeuThichResponse;
import com.example.shoppingweb.dto.request.DanhSachYeuThichRequest;
import com.example.shoppingweb.model.KhachHangModel;
import com.example.shoppingweb.model.SanPhamModel;

import java.util.List;

public interface IDanhSachYeuThichService {
    public List<DanhSachYeuThichResponse> findAll(String u);
    DanhSachYeuThichResponse save(DanhSachYeuThichRequest danhSachYeuThichRequest);
    DanhSachYeuThichResponse findById(String s);
    boolean existsById(String s);
    void deleteById(String s);
    void deleteDanhSachYeuThich(String nguoiSoHuu,String sanPham);
    Boolean exitByKhachHangAndSanPham(SanPhamModel modelSP, KhachHangModel modelKH);
    List<DanhSachYeuThichResponse> getByNguoiSoHuu(String maNguoiSoHuu);
}
