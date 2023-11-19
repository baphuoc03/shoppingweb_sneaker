package com.example.shoppingweb.service.impl;
import com.example.shoppingweb.model.AnhModel;
import com.example.shoppingweb.model.SanPhamModel;
import com.example.shoppingweb.repository.IAnhModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class AnhServiceImpl {
    @Autowired
    private IAnhModelRepository anhModelRepository;

    public <S extends AnhModel> List<S> saveAll(Iterable<S> entities) {
        return anhModelRepository.saveAll(entities);
    }

    public void deleteBySanPham(SanPhamModel sanPhamModel) {
        anhModelRepository.deleteBySanPham(sanPhamModel);
    }

    public Set<AnhModel> findAllBySanPham(SanPhamModel sanPhamModel) {
        return anhModelRepository.findAllBySanPham(sanPhamModel).stream().collect(Collectors.toSet());
    }
}
