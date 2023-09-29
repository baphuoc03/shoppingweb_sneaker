package com.example.shoppingweb.restcontroller.admin;

import com.example.shoppingweb.dto.reponse.NhanVienDtoResponse;
import com.example.shoppingweb.service.INhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${admin.domain}/nhan-vien")
public class NhanVienRestcontroller {

    @Autowired
    private INhanVienService nhanVienService;


    @GetMapping("get-all")
    public ResponseEntity<List<NhanVienDtoResponse>> getAllKhachHang(){
        return ResponseEntity.ok(nhanVienService.getAll());
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<NhanVienDtoResponse> getById(@PathVariable("id")String id){
        if(nhanVienService.existsByUsername(id)==false){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nhanVienService.findById(id));
    }

}
