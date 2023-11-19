package com.example.shoppingweb.repository;

import com.example.shoppingweb.model.ChiTietDonHangModel;
import com.example.shoppingweb.model.DonHangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IChiTietDonHangRepository extends JpaRepository<ChiTietDonHangModel,String> {
    List<ChiTietDonHangModel> findAllByDonHang(DonHangModel donHangModel);
}
