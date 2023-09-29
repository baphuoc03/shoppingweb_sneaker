package com.example.shoppingweb.repository;

import com.example.shoppingweb.model.KhachHangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IKhachHangRepository extends JpaRepository<KhachHangModel,String> {



}
