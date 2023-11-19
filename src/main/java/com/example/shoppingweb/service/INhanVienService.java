package com.example.shoppingweb.service;

import com.example.shoppingweb.dto.reponse.NhanVienDtoResponse;
import com.example.shoppingweb.dto.request.NhanVienDtoRequest;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface INhanVienService {

    Page<NhanVienDtoResponse> getAll(Integer page, Integer limit);

    NhanVienDtoResponse findById(String username);

    Boolean existsByUsername(String username);


    NhanVienDtoResponse add(NhanVienDtoRequest nhanVien) throws MessagingException, MessagingException;

    NhanVienDtoResponse update(NhanVienDtoRequest nhanVien) ;

    NhanVienDtoResponse update(NhanVienDtoRequest nhanVien, MultipartFile img) throws IOException;

    void deleteByUsername(String username);
}
