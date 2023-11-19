package com.example.shoppingweb.repository;

import com.example.shoppingweb.dto.reponse.DanhSachYeuThichResponse;
import com.example.shoppingweb.model.DanhSachYeuThichModel;
import com.example.shoppingweb.model.KhachHangModel;
import com.example.shoppingweb.model.SanPhamModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDanhSachYeuThichRepository extends JpaRepository<DanhSachYeuThichModel, String> {
    @Query("""
                    delete from DanhSachYeuThichModel d where d.nguoiSoHuu.username = ?1 and d.sanPham.ma = ?2
            """)
    void deleteDanhSachYeuThichKKK(String nguoiSoHuu, String sanPham);

    boolean existsBySanPhamAndNguoiSoHuu(SanPhamModel modelSP, KhachHangModel modelKhach);

    List<DanhSachYeuThichModel> getByNguoiSoHuu(KhachHangModel khachHangModel);

//    @Query("SELECT km  FROM DanhSachYeuThichModel km where km.sanPham = ?1 and km.nguoiSoHuu = ?2")
    DanhSachYeuThichModel getDanhSachYeuThichModelBySanPhamAndAndNguoiSoHuu(SanPhamModel sanPhamModel, KhachHangModel khachHangModel);

    @Query("""
        SELECT d FROM DanhSachYeuThichModel d where d.nguoiSoHuu.username = ?1
""")
    List<DanhSachYeuThichResponse> SearchDSYTByKhach(String username);


}
