package com.example.shoppingweb.repository;

import com.example.shoppingweb.model.DongSanPhamModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDongSanPhamRepository extends JpaRepository<DongSanPhamModel,String> {
}
