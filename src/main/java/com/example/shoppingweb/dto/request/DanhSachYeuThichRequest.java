package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.DanhSachYeuThichModel;
import com.example.shoppingweb.model.KhachHangModel;
import com.example.shoppingweb.model.SanPhamModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DanhSachYeuThichRequest {
    private String id;
    private String nguoiSoHuu;
    private String sanPham;

    public DanhSachYeuThichModel maptomodel(){
        DanhSachYeuThichModel ds = new DanhSachYeuThichModel();
        ds.setId(id);
        if(nguoiSoHuu != null && !nguoiSoHuu.isBlank()) ds.setNguoiSoHuu(new KhachHangModel(nguoiSoHuu));
         ds.setSanPham(new SanPhamModel(sanPham));
        return ds;
    };


}
