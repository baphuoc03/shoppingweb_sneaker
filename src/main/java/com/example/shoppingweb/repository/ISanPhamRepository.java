package com.example.shoppingweb.repository;

import com.example.shoppingweb.model.SanPhamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ISanPhamRepository extends JpaRepository<SanPhamModel, String> {

    @Transactional
    @Modifying
    @Query("""
            update SanPhamModel s SET s.hienThi = ?1 where s.ma = ?2
            """)
    Integer updateTrangThaiHIenThi(Boolean trangThai, String ma);

    List<SanPhamModel> findByMaIn(List<String> ma);

}
