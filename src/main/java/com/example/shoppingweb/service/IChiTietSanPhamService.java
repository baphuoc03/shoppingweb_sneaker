package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.ChiTietSanPhamDtoResponse;
import com.example.shoppingweb.dto.request.ChiTietSanPhamDtoRequest;

import java.util.List;

public interface IChiTietSanPhamService {
    List<ChiTietSanPhamDtoResponse> getAllBySanPhamMa(String maSP);

    ChiTietSanPhamDtoResponse updateSoLuong(ChiTietSanPhamDtoRequest request);

    Boolean existsBySanPhamMaAndSizeMa(String maSP, Float size);

    ChiTietSanPhamDtoResponse save(ChiTietSanPhamDtoRequest entity);

    ChiTietSanPhamDtoResponse update(ChiTietSanPhamDtoRequest entity);

    void delete(String id);


    List<ChiTietSanPhamDtoResponse> saveAll(List<Float> sizes, ChiTietSanPhamDtoRequest model);

    Boolean existsById(String id);
}
