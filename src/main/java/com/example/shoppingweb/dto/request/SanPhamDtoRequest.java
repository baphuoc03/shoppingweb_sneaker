package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SanPhamDtoRequest {
    @NotBlank(message = "Không để trống mã")
    @Size(max = 50, message = "Mã không quá 50 ký tự")
    private String ma;
    @NotBlank(message = "Không để trống tên")
    @Size(max = 50, message = "Tên không quá 50 ký tự")
    private String ten;
    private String mauSac;
    private String dongSanPham;
    private String kieuDang;
    private String chatLieu;
    private BigDecimal giaNhap;
    @NotNull(message = "Không để trống giá bán")
    @Min(value = 10000,message = "Giá bán phải lớn hơn 10.000đ ")
    private BigDecimal giaBan;
    private String moTa;
    private Date ngayTao;
    private Date ngayCapNhat;
    private Boolean hienThi;
    private List<String> anh;

    // thuộc tính để lọc
    private BigDecimal giaMax;

    public SanPhamDtoRequest(SanPhamModel model){
        ma = model.getMa();
        ten = model.getTen();
        mauSac = model.getMauSac() == null ? null : model.getMauSac().getMa();
        dongSanPham = model.getDongSanPham()== null ? null: model.getDongSanPham().getId();
        kieuDang = model.getKieuDang()== null ? null: model.getKieuDang().getId();
        chatLieu = model.getChatLieu()== null ? null: model.getChatLieu().getId();
        giaNhap = model.getGiaNhap();
        giaBan = model.getGiaBan();
        moTa = model.getMoTa();
        ngayTao = model.getNgayTao();
        ngayCapNhat = model.getNgayCapNhat();
        hienThi = model.getHienThi();
        anh = model.getImages().stream().map(i -> i.getTen()).collect(Collectors.toList());
        System.out.println(anh.size());
    }

    public SanPhamModel mapToModel(){
        SanPhamModel model = new SanPhamModel();
        model.setMa(ma);
        model.setTen(ten);
        if(mauSac != null && !mauSac.isBlank()) model.setMauSac(new MauSacModel(mauSac));
        if(dongSanPham != null && !dongSanPham.isBlank()) model.setDongSanPham(new DongSanPhamModel(dongSanPham));
        if(kieuDang != null && !kieuDang.isBlank()) model.setKieuDang(new KieuDangModel(kieuDang));
        if(chatLieu != null && !chatLieu.isBlank()) model.setChatLieu(new ChatLieuModel(chatLieu));
        model.setGiaNhap(giaNhap);
        model.setGiaBan(giaBan);
        model.setMoTa(moTa);
        model.setNgayTao(ngayTao);
        model.setNgayCapNhat(ngayCapNhat);
        model.setHienThi(hienThi);
        if(anh!=null) {
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

    public void setAnh(List<MultipartFile> file) throws IOException {
        if(file!=null){
            List<String> setAnh = new ArrayList<>();

            int i = 0;
            for (MultipartFile f : file) {
                byte[] bytes = f.getBytes();
                String typeImg = f.getContentType().split("/")[f.getContentType().split("/").length - 1];
                String imgName = "imgProduct" + this.ma + i + "." + typeImg;
                Path path = Paths.get("src/main/resources/static/admin/images/" + imgName);
                Path path1 = Files.write(path, bytes);
                System.out.println(path1.getFileName());
                setAnh.add(imgName);
                i++;
            }

            this.anh = setAnh;
            anh.forEach(a -> System.out.println(a));
        }

    }
    public void deleteImg(List<String> imgs) throws IOException {
        if(this.anh==null) return;
        for (String img:imgs) {
            System.out.println("asdas");
            Path fileToDeletePath = Paths.get("src/main/resources/static/admin/images/" + img);
            Files.delete(fileToDeletePath);
        }
    }



}
