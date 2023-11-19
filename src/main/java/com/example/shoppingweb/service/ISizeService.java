package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.SizeDTOResponse;
import com.example.shoppingweb.dto.request.SizeDTORequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISizeService {
    List<SizeDTOResponse> findAll();

    List<SizeDTOResponse> getByChieuDai(Float chieuDai);

    SizeDTOResponse save(SizeDTORequest sizeDTORequest);

    SizeDTOResponse findById(Float s);

    boolean existsById(Float s);

    void deleteById(Float s);
}
