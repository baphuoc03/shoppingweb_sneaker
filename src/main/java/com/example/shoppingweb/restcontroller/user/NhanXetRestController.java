package com.example.shoppingweb.restcontroller.user;

import com.example.shoppingweb.dto.request.NhanXetDtoRequest;
import com.example.shoppingweb.entitymanager.NhanXetEntityManager;
import com.example.shoppingweb.service.INhanXetService;
import com.example.shoppingweb.util.ValidateUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller("nhanXet-rest-user")
@RequestMapping("nhan-xet")
public class NhanXetRestController {
    @Autowired
    private INhanXetService nhanXetService;
    @Autowired
    private NhanXetEntityManager nhanXetEntityManager;

    @GetMapping()
    public ResponseEntity<?> getBySanPham(@RequestParam String maSP,
                                          @RequestParam(defaultValue = "0")Integer page,
                                          @RequestParam(defaultValue = "5")Integer limit,
                                          @RequestParam(required = false)Float rate){

        if(rate != null){
            return ResponseEntity.ok(nhanXetService.getNhanXetBySanPhamAndRate(page,limit,maSP,rate));
        }

        return ResponseEntity.ok(nhanXetService.getNhanXetBySanPham(page,limit,maSP));
    }

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody NhanXetDtoRequest nhanXetDtoRequest,
                                 BindingResult result,
                                 Authentication authentication){

        if(result.hasErrors()){
            return ValidateUtil.getErrors(result);
        }

        nhanXetDtoRequest.setKhachHang(authentication.getName());
        return ResponseEntity.ok(nhanXetService.add(nhanXetDtoRequest));
    }

    @GetMapping("avg-by-sanpham")
    public ResponseEntity<?> getAvgBySanPham(@RequestParam String maSP){
        return ResponseEntity.ok(nhanXetService.getAvgRatingBySanPham(maSP));
    }

    @GetMapping("avg-rates-by-sanpham")
    public ResponseEntity<?> getAvgRatesByMaSP(@RequestParam String maSP){
        return ResponseEntity.ok(nhanXetEntityManager.getAvgRatesByMaSP(maSP));
    }
}
