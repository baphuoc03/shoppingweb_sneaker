package com.example.shoppingweb.repository;

import com.example.shoppingweb.model.ThongBaoNhanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IThongBaoNhanRepository extends JpaRepository<ThongBaoNhanModel,Long> {
    @Query("""
SELECT t FROM ThongBaoNhanModel t 
WHERE t.nguoiNhan.id = ?1
ORDER BY t.thongBaoGui.thoiGianGui DESC 
""")
    List<ThongBaoNhanModel> getAllByNguoiNhanIdOrderByThongBaoGuiDesc(String idNguoiNhan);
}
