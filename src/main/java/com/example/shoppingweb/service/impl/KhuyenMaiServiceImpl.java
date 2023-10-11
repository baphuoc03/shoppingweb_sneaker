package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.reponse.KhuyenMaiResponse;
import com.example.shoppingweb.dto.request.KhuyenMaiRequest;
import com.example.shoppingweb.model.KhuyenMaiModel;
import com.example.shoppingweb.repository.KhuyenMaiRepository;
import com.example.shoppingweb.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {
    @Autowired
    KhuyenMaiRepository repository;

    @Override
    public Page<KhuyenMaiResponse> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<KhuyenMaiModel> pageModel = repository.findAll(pageable);
        return pageModel.map(x -> new KhuyenMaiResponse(x));
    }

    @Override
    public KhuyenMaiResponse findById(String id) {
        KhuyenMaiModel getById = repository.findById(id).get();
        return new KhuyenMaiResponse(getById);
    }

    @Override
    public void save(KhuyenMaiRequest khuyenMai) {
        repository.save(khuyenMai.mapToModel());
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
