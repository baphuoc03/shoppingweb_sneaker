package com.example.shoppingweb.service;

import com.example.shoppingweb.dto.reponse.NhanXetDtoResponse;
import com.example.shoppingweb.dto.request.NhanXetDtoRequest;
import org.springframework.data.domain.Page;

public interface INhanXetService {
    Page<NhanXetDtoResponse> getNhanXetBySanPham(Integer page, Integer limit, String maSP);

    Page<NhanXetDtoResponse> getNhanXetBySanPhamAndRate(Integer page, Integer limit, String maSP, Float rate);

    NhanXetDtoResponse add(NhanXetDtoRequest nhanXetDtoRequest);

    Float getAvgRatingBySanPham(String maSP);
}
