package com.example.shoppingweb.dto.reponse;

import com.example.shoppingweb.model.SanPhamModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SanPhamDtoResponse {
    private String ma;
    private String ten;
    private String mauSac;
    private String dongSanPham;
    private String xuatXu;
    private String kieuDang;
    private String chatLieu;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private String moTa;
    private Date ngayTao;
    private Date ngayCapNhat;
    private Boolean hienThi;
    private List<String> anh;
    private Long soLuong;

    public SanPhamDtoResponse(SanPhamModel model){
        ma = model.getMa();
        ten = model.getTen();
        mauSac = model.getMauSac() == null ? "" : model.getMauSac().getTen();
        dongSanPham = model.getDongSanPham()== null ? "" : model.getDongSanPham().getTen();
        xuatXu = model.getXuatXu() == null ? "" : model.getXuatXu().getTen();
        kieuDang = model.getKieuDang()== null ? "" : model.getKieuDang().getTen();
        chatLieu = model.getChatLieu()== null ? "" : model.getChatLieu().getTen();
        giaNhap = model.getGiaNhap();
        giaBan = model.getGiaBan();
        moTa = model.getMoTa();
        ngayTao = model.getNgayTao();
        ngayCapNhat = model.getNgayCapNhat();
        hienThi = model.getHienThi();
        if(model.getImages() != null)
        anh = model.getImages().stream()
//                .sorted(Comparator.comparing(AnhModel::getId))
                .map(img -> img.getTen())
                .collect(Collectors.toList());

        soLuong = model.getSoLuongSanPham();
    }
}
