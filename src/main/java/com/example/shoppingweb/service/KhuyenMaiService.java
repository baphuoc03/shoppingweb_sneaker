package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.KhuyenMaiResponse;
import com.example.shoppingweb.dto.request.KhuyenMaiRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface KhuyenMaiService {
    public Page<KhuyenMaiResponse> findAll(int pageNumber, int pageSize);

    public KhuyenMaiResponse findById(String id);

    public void save(KhuyenMaiRequest khuyenMai);

    public void delete(String id);

    public void updateGiamGiaWithNgayBD();

    public void updateGiamGiaWithNgayKT();
}
