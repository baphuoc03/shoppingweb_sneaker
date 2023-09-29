package com.example.shoppingweb.repository;

import com.example.shoppingweb.model.KieuDangModel;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface IKieuDangRepository extends JpaRepository<KieuDangModel, String> {
}
