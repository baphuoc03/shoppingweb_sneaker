package com.example.shoppingweb.service;

import com.example.shoppingweb.dto.reponse.NhanVienDtoResponse;
import com.example.shoppingweb.dto.request.NhanVienDtoRequest;
import jakarta.mail.MessagingException;

import java.util.List;

public interface INhanVienService {
    List<NhanVienDtoResponse> getAll();

    NhanVienDtoResponse findById(String username);

    Boolean existsByUsername(String username);


    NhanVienDtoResponse add(NhanVienDtoRequest nhanVien) throws MessagingException;

    NhanVienDtoResponse update(NhanVienDtoRequest nhanVien) ;
}
