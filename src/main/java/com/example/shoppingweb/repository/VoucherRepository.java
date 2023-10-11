package com.example.shoppingweb.repository;

import com.example.shoppingweb.model.VoucherModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<VoucherModel, String> {
    Page<VoucherModel> findByTenLike(String ten, Pageable pageable);
}
