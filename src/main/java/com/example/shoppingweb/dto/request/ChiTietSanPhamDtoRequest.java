package com.example.shoppingweb.dto.request;


import com.example.shoppingweb.model.ChiTietSanPhamModel;
import com.example.shoppingweb.model.SanPhamModel;
import com.example.shoppingweb.model.SizeModel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChiTietSanPhamDtoRequest {
    private String id;
    private String sanPham;
    private Float size;
    @Min(value = 0, message = "số lượng phải lớn hơn hoặc bằng 0")
    @Max(value = 1000000,message = "Số lượng phải bé hơn hoặc bằng 1000000")
    @NotNull(message = "Không để trống số lượng")
    private Long soLuong;

    public ChiTietSanPhamModel mapToModel(){
        ChiTietSanPhamModel model = new ChiTietSanPhamModel();
        model.setId(id);

        SanPhamModel sanPhamModel = new SanPhamModel();
        sanPhamModel.setMa(sanPham);
        model.setSanPham(sanPhamModel);

        SizeModel sizeModel = new SizeModel();
        sizeModel.setMa(size);
        model.setSize(sizeModel);

        model.setSoLuong(soLuong);
        return model;
    }
}
