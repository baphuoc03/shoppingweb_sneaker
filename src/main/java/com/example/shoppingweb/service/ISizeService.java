package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.SizeDTOResponse;
import com.example.shoppingweb.dto.request.SizeDTORequest;

import java.util.List;

public interface ISizeService {
    List<SizeDTOResponse> findAll();

    SizeDTOResponse save(SizeDTORequest sizeDTORequest);

    SizeDTOResponse findById(Float s);

    boolean existsById(Float s);

    void deleteById(Float s);
}
