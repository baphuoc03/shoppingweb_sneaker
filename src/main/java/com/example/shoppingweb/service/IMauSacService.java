package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.MauSacDTOResponse;
import com.example.shoppingweb.dto.request.MauSacDTORequest;

import java.util.List;

public interface IMauSacService {
   List<MauSacDTOResponse> findAll();
    public MauSacDTOResponse  save( MauSacDTORequest mauSacDTORequest);
    public MauSacDTOResponse findById(String s);
    public boolean existsById(String s);
    public void deleteById(String s);
}
