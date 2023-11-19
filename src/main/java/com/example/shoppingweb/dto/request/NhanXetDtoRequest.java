package com.example.shoppingweb.dto.request;


import com.example.shoppingweb.model.KhachHangModel;
import com.example.shoppingweb.model.NhanXetModel;
import com.example.shoppingweb.model.SanPhamModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;


import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NhanXetDtoRequest {
    private String id;
    private String khachHang;
    private String sanPham;
    @NotNull(message = "Có thể cho chúng tôi mức độ hài lòng của bạn")
    private Float rating;
    @NotBlank(message = "Không để trống tiêu đề")
    @Size(max = 50, message = "Tiêu đề tối đa 50 ký tự")
    private String tieuDe;
    @NotNull(message = "Không để trống nội dung")
    @Size(max = 200,message = "Nội dung tối đa 200 ký tự")
    private String noiDung;
    private Date thoiGian;

    public NhanXetModel mapToModel(){
        NhanXetModel model = new NhanXetModel();
        if(id!=null) model.setId(this.id);

        KhachHangModel khachHangModel = new KhachHangModel();
        khachHangModel.setUsername(khachHang);
        model.setKhachHang(khachHangModel);

        SanPhamModel sanPhamModel = new SanPhamModel();
        sanPhamModel.setMa(sanPham);
        model.setSanPham(sanPhamModel);

        model.setRating(this.rating);
        model.setTieuDe(this.tieuDe);
        model.setNoiDung(this.noiDung);
        model.setThoiGian(this.thoiGian);

        return model;
    }
}
