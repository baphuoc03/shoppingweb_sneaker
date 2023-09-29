package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.KieuDangDTOResponse;
import com.example.shoppingweb.dto.request.KieuDangDtoRequest;

import java.util.List;

public interface IKieuDangService {
    public List<KieuDangDTOResponse> findAll();
    public KieuDangDTOResponse save( KieuDangDtoRequest kieuDangDtoRequest);
    public KieuDangDTOResponse findById(String s);
    public boolean existsById(String s);
    public void deleteById(String s);
    public void deleteByIds(List<String> s);

}
