package com.example.shoppingweb.restcontroller.user;

import com.example.shoppingweb.dto.filter.SanPhamDtoFilter;
import com.example.shoppingweb.dto.reponse.SanPhamDtoResponse;
import com.example.shoppingweb.service.ISanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController("san-pham-user")
@RequestMapping("/san-pham")
public class SanPhamRestController {

    @Autowired
    private ISanPhamService sanPhamService;

    private Page<SanPhamDtoResponse> page = null;

    @GetMapping("get-all")
    public ResponseEntity<Page<SanPhamDtoResponse>> getAll(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                           @RequestParam(value = "limit", defaultValue = "8") Integer limit) {

        return ResponseEntity.ok(sanPhamService.paginationInUser(pageNumber, limit));
    }
    @GetMapping("thuong-hieu/{idThuongHieu}")
    public ResponseEntity<Page<SanPhamDtoResponse>> getByThuongHieu(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                           @RequestParam(value = "limit", defaultValue = "8") Integer limit,
                                                            @PathVariable("idThuongHieu")String thuongHieu) {

        return ResponseEntity.ok(sanPhamService.paginationInUserByThuongHieu(pageNumber, limit,thuongHieu));
    }

    @GetMapping("san-pham-tuong-tu/{ma}")
    public ResponseEntity<List<SanPhamDtoResponse>> getSpTuongTu(@PathVariable("ma")String ma){
        return ResponseEntity.ok(sanPhamService.getSanPhamTuongTu(ma));
    }


    @PostMapping("filter")
    public ResponseEntity<Page<SanPhamDtoResponse>> filter(@RequestBody SanPhamDtoFilter sanPhamDtoFilter,
                                                           @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                           @RequestParam(value = "limit", defaultValue = "8") Integer limit) {
        return ResponseEntity.ok(sanPhamService.filterInUser(sanPhamDtoFilter,pageNumber,limit));
    }

    @GetMapping("{ma}")
    public ResponseEntity<?> detail(@PathVariable("ma")String ma){
        if(!sanPhamService.existsById(ma)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sanPhamService.findByMa(ma));
    }

    @GetMapping("ban-chay")
    public ResponseEntity<?> getBanChay(@RequestParam(defaultValue = "4")Integer limit){
        return ResponseEntity.ok(sanPhamService.getBanChay(limit));
    }

    @GetMapping("khuyen-mai")
    public ResponseEntity<?> getKhuyenMai(@RequestParam(defaultValue = "4")Integer limit){
        return ResponseEntity.ok(sanPhamService.getKhuyenMai(limit));
    }

    @GetMapping("san-pham-moi")
    public ResponseEntity<?> getSanPhamMoi(@RequestParam(defaultValue = "4")Integer limit){
        return ResponseEntity.ok(sanPhamService.getSanPhamMoi(limit));
    }

}
