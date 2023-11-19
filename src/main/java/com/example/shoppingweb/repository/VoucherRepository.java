package com.example.shoppingweb.repository;


import com.example.shoppingweb.model.VoucherModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VoucherRepository extends JpaRepository<VoucherModel, String> {
    Page<VoucherModel> findByTenLike(String ten, Pageable pageable);

    @Query("SELECT vc  FROM VoucherModel vc where vc.giaTriToiThieu >= :sumTotalBill")
    List<VoucherModel> disabledVoucher(@Param("sumTotalBill") Double sumTotalBill);

    @Query("SELECT vc  FROM VoucherModel vc where current_date between vc.ngayBatDau  and vc.ngayKetThuc and vc.soLuong > 0")
    List<VoucherModel> findVoucherEligible();

    @Transactional
    @Modifying
    @Query("""
            UPDATE VoucherModel v SET v.soLuong = ?1 WHERE v.ma = ?2
            """)
    int updateSoLuong(int soLuong, String id);

    List<VoucherModel> findAllByOrderByTenDesc();

    List<VoucherModel> findAllByOrderByTenAsc();

    List<VoucherModel> findAllByOrderByMucGiamAsc();

    List<VoucherModel> findAllByOrderByMucGiamDesc();

    List<VoucherModel> findAllByOrderByNgayBatDauAsc();
    List<VoucherModel> findAllByOrderByNgayBatDauDesc();

    List<VoucherModel> findAllByOrderBySoLuongDesc();

    List<VoucherModel> findAllByOrderBySoLuongAsc();

    List<VoucherModel> findAllByOrderByGiaTriToiThieuDesc();

    List<VoucherModel> findAllByOrderByGiaTriToiThieuAsc();

}
