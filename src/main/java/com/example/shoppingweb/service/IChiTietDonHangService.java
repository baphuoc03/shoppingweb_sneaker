package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.ChiTietDonHangDtoResponse;
import com.example.shoppingweb.dto.request.ChiTietDonHangDTORequest;

import java.util.List;

public interface IChiTietDonHangService {
    List<ChiTietDonHangDtoResponse> getByDonHang(String maDonHang);

    ChiTietDonHangDtoResponse findById(String id);
    void save(ChiTietDonHangDTORequest chiTietDonHang);
}
