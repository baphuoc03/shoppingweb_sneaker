package com.example.shoppingweb.restcontroller.user;


import com.example.shoppingweb.dto.reponse.GioHangDtoReponse;
import com.example.shoppingweb.repository.IChiTietSanPhamRepository;
import com.example.shoppingweb.service.IChiTietSanPhamService;
import com.example.shoppingweb.service.IGioHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class GioHangRestController {
    @Autowired
    private IGioHangService service;
    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private IChiTietSanPhamRepository chiTietSanPhamRepository;
    @GetMapping("/find-all")
    public ResponseEntity<List<GioHangDtoReponse>> getCartContents() {
        List<GioHangDtoReponse> cartContents = service.laySpTrongGio();
        return new ResponseEntity<>(cartContents, HttpStatus.OK);
    }

    @PostMapping("add-to-cart")
    public ResponseEntity<?> addToCart(@RequestParam(value = "idCTSP",required = false)String idCTSP,
                                             @RequestParam("sl")Integer sl){
        Map<String,String> er = new HashMap<>();
        if(idCTSP==null || idCTSP.length()==0){
            er.put("eSize","Vui lòng chọn size");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
        }
        if(!chiTietSanPhamService.checkSoLuongSP(idCTSP, Long.valueOf(sl))){
            er.put("eSize","Số lượng không hợp lệ!!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
        }


        service.addOrUpdateToCart(idCTSP,sl);
        return ResponseEntity.ok(service.laySpTrongGio());
    }
    @PutMapping("update-sl/{idCTSP}/{sl}")
    public ResponseEntity<List<GioHangDtoReponse>> updateSL(@PathVariable("idCTSP")String idCTSP,@PathVariable("sl")Integer sl){
//        Long slSanPham = chiTietSanPhamRepository.getReferenceById(idCTSP).getSoLuong();
//        if(slSanPham < sl || sl <= 0) return null;
        service.updateSoLuong(idCTSP,sl);
        return ResponseEntity.ok(service.laySpTrongGio());
    }
    @DeleteMapping("/remove/{key}")
    public  List<GioHangDtoReponse>removeProductInCart(@PathVariable("key")String idCTSP){
        service.removeProductInCart(idCTSP);
        return service.laySpTrongGio();
    }


}
