package com.example.shoppingweb.restcontroller.admin;

import com.example.shoppingweb.dto.filter.SanPhamDtoFilter;
import com.example.shoppingweb.dto.reponse.SanPhamDtoResponse;
import com.example.shoppingweb.service.ISanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${admin.domain}/san-pham")
public class SanPhamRestController {

    @Autowired
    private ISanPhamService sanPhamService;

    @GetMapping("get-all")
    public ResponseEntity<List<SanPhamDtoResponse>> getAll(){
        return ResponseEntity.ok(sanPhamService.findAll());
    }

    @DeleteMapping("delete/{id}")
    @Transactional(rollbackFor =  {Exception.class, Throwable.class})//Khi có lỗi sẽ rollback
    public ResponseEntity<?> delete(@PathVariable("id")String ma) throws IOException {

        if(!sanPhamService.existsById(ma)){//Kiểm tra xem mã tồn tại ko
            return ResponseEntity.status(404).body("Mã sản phẩm không hợp lệ");
        }

        String tenSanPham = sanPhamService.findByMa(ma).getMa() + " - " + sanPhamService.findByMa(ma).getTen();

        sanPhamService.deleteById(ma);

        //Tạo và gửi thông báo
//        ThongBaoModel thongBao = new ThongBaoModel(null,null, ThongBaoType.Delete.name(),"Xóa sản phẩm: "+tenSanPham,new Date(),null);
//        SocketUtil.sendNotification(thongBao);

        return ResponseEntity.ok().build();
    }

    @PutMapping("update-TrangThai-HienThi/{id}")
    public ResponseEntity<?> updateTrangThaiHienThi(@PathVariable("id")String ma,@RequestBody Boolean trangThai){
        if(!sanPhamService.existsById(ma)){//Kiểm tra xem mã tồn tại ko
            return ResponseEntity.status(404).body("Mã sản phẩm không hợp lệ");
        }
        return ResponseEntity.ok(sanPhamService.updateTrangThaiHIenThi(trangThai,ma));
    }

    @PostMapping("filter")
    public ResponseEntity<List<SanPhamDtoResponse>> filter(@RequestBody SanPhamDtoFilter sanPhamDtoFilter ){
        return ResponseEntity.ok(sanPhamService.filter(sanPhamDtoFilter));
    }

}
