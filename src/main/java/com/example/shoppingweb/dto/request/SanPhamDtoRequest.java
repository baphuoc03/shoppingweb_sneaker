package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.*;
import com.example.shoppingweb.util.ImgUtil;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SanPhamDtoRequest {
    @NotBlank(message = "Không để trống mã")
    @Size(max = 20, message = "Mã không quá 20 ký tự")
    @Pattern(regexp = "[A-Z0-9]*", message = "Mã sản phẩm không chỉ chứa số và chữ cái. Không bao gồm ký tự có dấu")
    private String ma;
    @NotBlank(message = "Không để trống tên")
    @Size(max = 50, message = "Tên không quá 50 ký tự")
    private String ten;
    private String mauSac;
    private String dongSanPham;
    private String xuatXu;
    private String kieuDang;
    private String chatLieu;
    private BigDecimal giaNhap;
    @NotNull(message = "Không để trống giá bán")
    @Min(value = 10000, message = "Giá bán phải lớn hơn 10.000đ ")
    private BigDecimal giaBan;
    private String moTa;
    private Date ngayTao;
    private Date ngayCapNhat;
    private Boolean hienThi;
    private List<String> anh = new ArrayList<>();

    // thuộc tính để lọc
    private BigDecimal giaMax;

    public SanPhamDtoRequest(SanPhamModel model) {
        ma = model.getMa();
        ten = model.getTen();
        mauSac = model.getMauSac() == null ? null : model.getMauSac().getMa();
        dongSanPham = model.getDongSanPham() == null ? null : model.getDongSanPham().getId();
        xuatXu = model.getXuatXu() == null ? null : model.getXuatXu().getId();
        kieuDang = model.getKieuDang() == null ? null : model.getKieuDang().getId();
        chatLieu = model.getChatLieu() == null ? null : model.getChatLieu().getId();
        giaNhap = model.getGiaNhap();
        giaBan = model.getGiaBan();
        moTa = model.getMoTa();
        ngayTao = model.getNgayTao();
        ngayCapNhat = model.getNgayCapNhat();
        hienThi = model.getHienThi();
        anh = model.getImages().stream().map(i -> i.getTen()).collect(Collectors.toList());
        System.out.println(anh.size());
    }

    public SanPhamModel mapToModel() {
        SanPhamModel model = new SanPhamModel();
        model.setMa(ma);
        model.setTen(ten);
        if (mauSac != null && !mauSac.isBlank()) model.setMauSac(new MauSacModel(mauSac));
        if (dongSanPham != null && !dongSanPham.isBlank()) model.setDongSanPham(new DongSanPhamModel(dongSanPham));
        if (kieuDang != null && !kieuDang.isBlank()) model.setKieuDang(new KieuDangModel(kieuDang));
        if (chatLieu != null && !chatLieu.isBlank()) model.setChatLieu(new ChatLieuModel(chatLieu));


        if(xuatXu != null && !xuatXu.isBlank()) {
            XuatXuModel xuatXu = new XuatXuModel();
            xuatXu.setId(this.xuatXu);
            model.setXuatXu(xuatXu);
        }
        model.setGiaNhap(giaNhap);
        model.setGiaBan(giaBan);
        model.setMoTa(moTa);
        model.setNgayTao(ngayTao);
        model.setNgayCapNhat(ngayCapNhat);
        model.setHienThi(hienThi);
        if (anh != null) {
            List<AnhModel> images = anh.stream().map(anh -> {
                AnhModel img = new AnhModel();
                img.setTen(anh);
                SanPhamModel sanPhamModel = new SanPhamModel();
                sanPhamModel.setMa(ma);
                img.setSanPham(sanPhamModel);
                return img;
            }).collect(Collectors.toList());
            model.setImages(images);
        }

        return model;
    }

    public void setAnh(List<MultipartFile> file, Set<String> oldImages) throws IOException {

        List<String> newImages = file.stream().map(f -> f.getOriginalFilename()).collect(Collectors.toList());

        oldImages.forEach(img -> {
            if (!newImages.contains(img)) {
                ImgUtil.deleteImg(img, "product");
            }
        });

        if (file != null) {
            for (MultipartFile f : file) {
                if (!oldImages.contains(f.getOriginalFilename())) {
                    try {
                        this.anh.add(ImgUtil.addImage(f, "product"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else this.anh.add(f.getOriginalFilename());
            }
        }

    }

    public void setAnh(List<MultipartFile> file) throws IOException {
        if (file != null) {
            this.anh = ImgUtil.addImages(file,"product");
        }

    }
}
