package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.NhanVienDtoResponse;

import java.util.List;

public interface INhanVienService {
    List<NhanVienDtoResponse> getAll();

    NhanVienDtoResponse findById(String username);

    Boolean existsByUsername(String username);
}
