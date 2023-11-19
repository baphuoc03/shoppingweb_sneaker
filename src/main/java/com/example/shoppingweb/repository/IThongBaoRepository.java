package com.example.shoppingweb.repository;


import com.example.shoppingweb.model.ThongBaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IThongBaoRepository extends JpaRepository<ThongBaoModel,String> {

}
