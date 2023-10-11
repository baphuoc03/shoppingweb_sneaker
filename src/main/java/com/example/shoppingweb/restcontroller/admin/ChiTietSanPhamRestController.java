package com.example.shoppingweb.restcontroller.admin;

import com.example.shoppingweb.dto.reponse.ChiTietSanPhamDtoResponse;
import com.example.shoppingweb.dto.request.ChiTietSanPhamDtoRequest;
import com.example.shoppingweb.model.SizeModel;
import com.example.shoppingweb.service.IChiTietSanPhamService;
import com.example.shoppingweb.util.ValidateUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.example.shoppingweb.repository.sizerepo;

import java.util.List;

@RestController
@RequestMapping("${admin.domain}/san-pham/{maSP}")
public class ChiTietSanPhamRestController {

    @Autowired
    private sizerepo sizerepo;
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
    public ResponseEntity<?> add(@Valid @RequestBody ChiTietSanPhamDtoRequest model,
                                 BindingResult result,
                                 @PathVariable("maSP")String maSP,
                                 @RequestParam("sizes")List<Float> size){
        if(size.size()==0){
            result.addError(new FieldError("eSize","eSize","Vui lòng chọn size"));
            if(!result.hasErrors()) ValidateUtil.getErrors(result);
        }
        if(result.hasErrors()){
            return ValidateUtil.getErrors(result);
        }
        model.setSanPham(maSP);
        return ResponseEntity.ok(chiTietSanPhamService.saveAll(size,model));
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
