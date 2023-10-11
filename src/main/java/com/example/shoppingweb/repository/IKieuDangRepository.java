package com.example.shoppingweb.repository;

import com.example.shoppingweb.model.KieuDangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IKieuDangRepository extends JpaRepository<KieuDangModel, String> {


    @Transactional
    @Modifying
    @Query("""
UPDATE KieuDangModel s SET s.ten = ?1 WHERE s.id = ?2
""")
    int update(String ten, String id);
}
