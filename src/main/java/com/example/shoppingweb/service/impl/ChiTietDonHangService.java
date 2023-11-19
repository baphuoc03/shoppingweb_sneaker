package com.example.shoppingweb.service.impl;
import com.example.shoppingweb.dto.reponse.ChiTietDonHangDtoResponse;
import com.example.shoppingweb.dto.request.ChiTietDonHangDTORequest;
import com.example.shoppingweb.model.DonHangModel;
import com.example.shoppingweb.repository.IChiTietDonHangRepository;
import com.example.shoppingweb.service.IChiTietDonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChiTietDonHangService implements IChiTietDonHangService {
    @Autowired
    private IChiTietDonHangRepository chiTietDonHangRepository;

    @Override
    public List<ChiTietDonHangDtoResponse> getByDonHang(String maDonHang) {
        DonHangModel donHangModel = new DonHangModel();
        donHangModel.setMa(maDonHang);
        return chiTietDonHangRepository.findAllByDonHang(donHangModel).stream().map(d -> new ChiTietDonHangDtoResponse(d)).collect(Collectors.toList());
    }

    @Override
    public ChiTietDonHangDtoResponse findById(String id) {
        return new ChiTietDonHangDtoResponse(chiTietDonHangRepository.findById(id).get());
    }
    @Override
    public void save(ChiTietDonHangDTORequest chiTietDonHang) {
        chiTietDonHangRepository.save(chiTietDonHang.mapModel());
    }
}
