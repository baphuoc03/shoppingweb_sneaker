package com.example.shoppingweb.service;

import com.example.shoppingweb.dto.reponse.KhachHangDtoResponse;
import com.example.shoppingweb.dto.request.KhachHangDTORequest;
import com.example.shoppingweb.model.DiaChiModel;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IKhachHangService {

    Page<KhachHangDtoResponse> getAll(Integer page, Integer limit);

    KhachHangDtoResponse findById(String username);
    List<DiaChiModel> diaChiByTaiKhoan(String taiKhoan);
    Boolean exsistsByUsername(String username);
    KhachHangDtoResponse add(KhachHangDTORequest khachHang) throws MessagingException, MessagingException;

    KhachHangDtoResponse update(KhachHangDTORequest khachHang);

    KhachHangDtoResponse update(KhachHangDTORequest khachHang, MultipartFile img) throws IOException;

    void deleteByUsername(String username);
//    List<DiaChiModel> diaChiByTaiKhoan(String taiKhoan);
}
