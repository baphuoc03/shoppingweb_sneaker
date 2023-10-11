package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.XuatXuResponse;
import com.example.shoppingweb.dto.request.XuatXuRequest;

import java.util.List;

public interface IXuatXuService {
    List<XuatXuResponse> findAll();
    XuatXuResponse save(XuatXuRequest request);
    XuatXuResponse findById(String s);
    boolean existsById(String s);
    void deleteById(String s);
}
