package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.ThuongHieuDtoResponse;
import com.example.shoppingweb.dto.request.ThuongHieuDtoRequest;

import java.util.List;

public interface IThuongHieuService {
    public List<ThuongHieuDtoResponse> findAll();
    public ThuongHieuDtoResponse save( ThuongHieuDtoRequest thuongHieuDtoRequest);
    public ThuongHieuDtoResponse findById(String s);
    public boolean existsById(String s);
    public void deleteById(String s);
    public void deleteByIds(List<String> s);
}
