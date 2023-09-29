package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.reponse.DongSanPhamResponese;
import com.example.shoppingweb.dto.request.DongSanPhamRequest;
import com.example.shoppingweb.model.DongSanPhamModel;
import com.example.shoppingweb.repository.IDongSanPhamRepository;
import com.example.shoppingweb.service.IDongSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DongSanPhamServiceImpl implements IDongSanPhamService {
    @Autowired
    IDongSanPhamRepository repo;

    public List<DongSanPhamResponese> findAll() {
        return repo.findAll().stream()
                .map(d -> new DongSanPhamResponese(d))
                .collect(Collectors.toList());
    }

    public DongSanPhamResponese save(DongSanPhamRequest dongSanPhamDtoRequest) {
        DongSanPhamModel model = repo.save(dongSanPhamDtoRequest.maptomodel());
        return new DongSanPhamResponese(model);
    }

    public DongSanPhamResponese findById(String s) {
        DongSanPhamModel model = repo.findById(s).get();
        return new DongSanPhamResponese(model);
    }

    public boolean existsById(String s) {
        return repo.existsById(s);
    }

    public void deleteById(String s) {
        repo.deleteById(s);
    }
}
