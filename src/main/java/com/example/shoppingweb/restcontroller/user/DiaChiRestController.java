package com.example.shoppingweb.restcontroller.user;

import com.example.shoppingweb.dto.reponse.KhachHangDtoResponse;
import com.example.shoppingweb.model.DiaChiModel;
import com.example.shoppingweb.model.KhachHangModel;
import com.example.shoppingweb.service.IDiaChiService;
import com.example.shoppingweb.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DiaChiRestController {
    @Autowired
    IDiaChiService diaChiService;
    @Autowired
    IKhachHangService khachHangService;

    @PostMapping("/dia-chi-by-id")
    public ResponseEntity<?> diaChiFindID(@RequestBody Map<String, Long> request) {
        Long id = request.get("id");
        return ResponseEntity.ok(diaChiService.findById(id));
    }

    @PostMapping("/dia-chi")
    public ResponseEntity<?> saveDiaChi(@RequestBody DiaChiModel diaChiModel, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String khachHang = authentication.getName();
            KhachHangModel khachHangModel = new KhachHangModel();
            khachHangModel.setUsername(khachHang);
            diaChiModel.setTaiKhoan(khachHangModel);
            diaChiService.addDiaChi(diaChiModel);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Vui lòng đăng nhập để truy cập thông tin.");
    }

    @GetMapping("/dia-chi-khach-hang")
    public ResponseEntity<?> listDiaChiByTaiKhoan(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String khachHang = authentication.getName();
            KhachHangDtoResponse kh = khachHangService.findById(khachHang);

            if (kh != null) {
                return ResponseEntity.ok(khachHangService.diaChiByTaiKhoan(khachHang));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Đăng nhập");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Vui lòng đăng nhập để truy cập thông tin.");
        }
    }
}
