package com.example.shoppingweb.repository;

import com.example.shoppingweb.model.AnhModel;
import com.example.shoppingweb.model.SanPhamModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAnhModelRepository extends JpaRepository<AnhModel,Long> {

    void deleteBySanPham(SanPhamModel sanPhamModel);

    List<AnhModel> findAllBySanPham(SanPhamModel sanPhamModel);
}
