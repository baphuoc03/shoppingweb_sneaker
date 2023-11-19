package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.SizeModel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@NotNull
public class SizeDTORequest {
    @NotNull(message = "Không để trống mã")
    @Max(value = 50, message = "Mã không quá 50 ký tự")
    private Float ma;
    @NotNull(message = "Không để trống chiều dài")
    @Max(value = 50, message = "Chiều dài không quá 50 ký tự")
    private Float chieuDai;
    private Date ngayTao;
    private Date ngayCapNhat;

    public SizeModel mapToModel() {
        SizeModel model = new SizeModel();
        model.setMa(ma);
        model.setChieuDai(chieuDai);
        model.setNgayTao(ngayTao);
        model.setNgayCapNhat(ngayCapNhat);
        return model;
    }
    public SizeDTORequest(SizeModel model){
        ma = model.getMa();
        chieuDai = model.getChieuDai();
        ngayTao = model.getNgayTao();
        ngayCapNhat = model.getNgayCapNhat();
    }
}
