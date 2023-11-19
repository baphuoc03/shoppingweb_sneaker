package com.example.shoppingweb.repository;
import com.example.shoppingweb.model.DiaChiModel;
import com.example.shoppingweb.model.KhachHangModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IKhachHangRepository extends JpaRepository<KhachHangModel, String> {
    @Query("SELECT  kh.danhSachDiaChi FROM KhachHangModel kh where kh.username = :taiKhoan")
    List<DiaChiModel> findAllDiaChiByTaiKhoan(@Param("taiKhoan") String taiKhoan);



}
