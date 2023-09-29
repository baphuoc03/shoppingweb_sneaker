package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.KhachHangDtoResponse;

import java.util.List;

public interface IKhachHangService {

    List<KhachHangDtoResponse> getAll();

    KhachHangDtoResponse findById(String username);
}
