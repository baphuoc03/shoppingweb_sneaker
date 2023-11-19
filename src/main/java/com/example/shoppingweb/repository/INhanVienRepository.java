package com.example.shoppingweb.repository;


import com.example.shoppingweb.model.NhanVienModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INhanVienRepository extends JpaRepository<NhanVienModel,String> {
}
