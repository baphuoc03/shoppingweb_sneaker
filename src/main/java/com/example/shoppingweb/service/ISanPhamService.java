package com.example.shoppingweb.service;

import com.example.shoppingweb.dto.filter.SanPhamDtoFilter;
import com.example.shoppingweb.dto.reponse.SanPhamDtoResponse;
import com.example.shoppingweb.dto.request.SanPhamDtoRequest;
import com.example.shoppingweb.model.SanPhamModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ISanPhamService {
    List<SanPhamDtoResponse> findAll();

    SanPhamDtoResponse findByMa(String ma);

    SanPhamDtoRequest findDtoRequetsByMa(String ma);

    List<SanPhamDtoResponse> saveAll(List<SanPhamDtoRequest> sanPham);

    List<SanPhamModel> findListById(List<String> ma);

    SanPhamDtoResponse save(SanPhamDtoRequest entity);

    SanPhamDtoResponse update(SanPhamDtoRequest entity) throws IOException;

    boolean existsById(String s);

    void deleteById(String s) throws IOException;

    Integer updateTrangThaiHIenThi(Boolean trangThai, String ma);

    List<SanPhamDtoResponse> filter(SanPhamDtoFilter sanPhamDtoFilter);
}
