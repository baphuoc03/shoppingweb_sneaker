package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.reponse.KhachHangDtoResponse;
import com.example.shoppingweb.repository.IKhachHangRepository;
import com.example.shoppingweb.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KhachHangServiceImpl implements IKhachHangService {
    @Autowired
    IKhachHangRepository khachHangRepository;

    @Override
    public List<KhachHangDtoResponse> getAll(){
        return khachHangRepository.findAll().stream().map(k -> new KhachHangDtoResponse(k))
                .collect(Collectors.toList());
    }

    @Override
    public KhachHangDtoResponse findById(String username){
        return new KhachHangDtoResponse(khachHangRepository.findById(username).get());
    }

}
