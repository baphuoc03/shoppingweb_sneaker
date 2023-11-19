package com.example.shoppingweb.dto.reponse;
import com.example.shoppingweb.model.DiaChiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowSysOut;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiaChiDTOResponse {

    private Long id;
    private Integer thanhPhoCode;
    private Integer quanHuyenCode;
    private String xaPhuongCode;
    private String thanhPhoName;
    private String quanHuyenName;
    private String xaPhuongName;
    private String diaChiChiTiet;
//    private KhachHangDtoResponse taiKhoan;

    public DiaChiDTOResponse(DiaChiModel model) {
        this.id = model.getId();
        this.thanhPhoCode = model.getThanhPhoCode();
        this.xaPhuongCode = model.getXaPhuongCode();
        this.thanhPhoName = model.getThanhPhoName();
        this.quanHuyenName = model.getQuanHuyenName();
        this.quanHuyenCode = model.getQuanHuyenCode();
        this.xaPhuongName = model.getXaPhuongName();
        this.diaChiChiTiet = model.getDiaChiChiTiet();
//        this.taiKhoan = new KhachHangDtoResponse(model.getTaiKhoan());
    }

}
