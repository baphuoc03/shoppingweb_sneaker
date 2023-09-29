package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.DongSanPhamResponese;
import com.example.shoppingweb.dto.request.DongSanPhamRequest;

import java.util.List;

public interface IDongSanPhamService {
    public List<DongSanPhamResponese> findAll();
    public DongSanPhamResponese save(DongSanPhamRequest dongSanPhamDtoRequest);
    public DongSanPhamResponese findById(String s);
    public boolean existsById(String s);
    public void deleteById(String s);
}
