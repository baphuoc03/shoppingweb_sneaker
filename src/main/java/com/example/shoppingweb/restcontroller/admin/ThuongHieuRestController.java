package com.example.shoppingweb.restcontroller.admin;

import com.example.shoppingweb.ResponseEntity.ResponseObject;
import com.example.shoppingweb.dto.reponse.ThuongHieuDtoResponse;
import com.example.shoppingweb.dto.request.ThuongHieuDtoRequest;
import com.example.shoppingweb.service.IThuongHieuService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${admin.domain}/thuong-hieu")
public class ThuongHieuRestController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IThuongHieuService service;
    @GetMapping("/find-all")
    public List<ThuongHieuDtoResponse> findAll(){
        return service.findAll();
    }
    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody ThuongHieuDtoRequest thuonghieu ){
        return ResponseEntity.ok(service.save(thuonghieu));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ThuongHieuDtoRequest thuonghieu, @PathVariable("id") String id){
//        service.findById(id);
//        ThuongHieuDtoResponse model = service.save(thuonghieu);
//        return ResponseEntity.ok(model);
        boolean exitst = service.existsById(id);
        if (exitst) {
            thuonghieu.setId(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Oke", "Sửa thành công", service.save(thuonghieu))
            );
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject("Found", "Không tìm thấy", "")
        );
    }
    @DeleteMapping("deletes")
    public ResponseEntity<?> deletes(@RequestBody List<String> id){
        service.deleteByIds(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ThuongHieuDtoResponse findById(@PathVariable("id") String id){
        return service.findById(id);
    }

}
