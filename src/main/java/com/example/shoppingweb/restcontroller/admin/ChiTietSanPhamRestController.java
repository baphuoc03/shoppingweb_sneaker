package com.example.shoppingweb.restcontroller.admin;

import com.example.shoppingweb.dto.reponse.ChiTietSanPhamDtoResponse;
import com.example.shoppingweb.dto.request.ChiTietSanPhamDtoRequest;
import com.example.shoppingweb.model.SizeModel;
import com.example.shoppingweb.service.IChiTietSanPhamService;
import com.example.shoppingweb.util.ValidateUtil;
import com.example.shoppingweb.repository.sizerepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${admin.domain}/san-pham/{maSP}")
public class ChiTietSanPhamRestController {

    @Autowired
    private sizerepo sizerepo ;
    @Autowired
    private IChiTietSanPhamService sanPhamService;

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("get-all")
    public ResponseEntity<List<ChiTietSanPhamDtoResponse>> getAll(@PathVariable("maSP")String maSP){

        return ResponseEntity.ok(chiTietSanPhamService.getAllBySanPhamMa(maSP));

    }

    @GetMapping("test")
    public List<SizeModel> test(@PathVariable("maSP")String maSP){
        return sizerepo.getAllNotInSanPham(maSP);
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestParam("soLuong")Long soLuong, @RequestBody List<ChiTietSanPhamDtoRequest> models){
        if(soLuong<0){
            Map<String, String> body = new HashMap<>();
            body.put("message", "Số lượng phải >= 0");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.valueOf("application/json")).body(body);
        }
        return ResponseEntity.ok(chiTietSanPhamService.saveAll(models));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")String id){
        if(!chiTietSanPhamService.existsById(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        chiTietSanPhamService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@Valid @RequestBody ChiTietSanPhamDtoRequest model,
                                    BindingResult result){

        if(result.hasErrors()){
            return ValidateUtil.getErrors(result);
        }
        return ResponseEntity.ok(chiTietSanPhamService.update(model));
    }


}
