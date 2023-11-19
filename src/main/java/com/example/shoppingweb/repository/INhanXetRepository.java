package com.example.shoppingweb.repository;

import com.example.shoppingweb.model.NhanXetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface INhanXetRepository extends JpaRepository<NhanXetModel,String> {
    @Query("""
    SELECT n FROM NhanXetModel n 
    WHERE n.sanPham.ma = ?1
    ORDER BY n.thoiGian DESC 
""")
    Page<NhanXetModel> getBySanPhamMa(String maSanPham, Pageable pageable);

    @Query("""
    SELECT n FROM NhanXetModel n 
    WHERE n.sanPham.ma = ?1 AND n.rating = ?2
    ORDER BY n.thoiGian DESC 
""")
    Page<NhanXetModel> getBySanPhamMaAndRate(String maSanPham, Float rate, Pageable pageable);

    @Query("""
    SELECT AVG(n.rating) FROM NhanXetModel n
    WHERE n.sanPham.ma = ?1
""")
    Float getAvgRatingBySanPham(String maSP);
}
