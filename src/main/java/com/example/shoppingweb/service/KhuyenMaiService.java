package com.example.shoppingweb.service;

import com.example.shoppingweb.dto.reponse.KhuyenMaiResponse;
import com.example.shoppingweb.dto.request.KhuyenMaiRequest;
import org.springframework.data.domain.Page;

public interface KhuyenMaiService {
    public Page<KhuyenMaiResponse> findAll(int pageNumber, int pageSize);

    public KhuyenMaiResponse findById(String id);

    public void save(KhuyenMaiRequest khuyenMai);

    public void delete(String id);
}
