package com.example.shoppingweb.restcontroller.admin;

import com.example.shoppingweb.dto.reponse.NhanVienDtoResponse;
import com.example.shoppingweb.dto.request.NhanVienDtoRequest;
import com.example.shoppingweb.service.INhanVienService;
import com.example.shoppingweb.util.ValidateUtil;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public ResponseEntity<?> add(@Valid @RequestBody NhanVienDtoRequest nhanVien,
                                 BindingResult result) throws MessagingException {
        if(nhanVien.getUsername()!=null && !nhanVien.getUsername().isBlank()){
            if(nhanVienService.existsByUsername(nhanVien.getUsername())){
                result.addError(new FieldError("username","username","Username đã tồn tại"));
                if(!result.hasErrors()) return ValidateUtil.getErrors(result);
            }
        }
        if(result.hasErrors()) return ValidateUtil.getErrors(result);
        return ResponseEntity.ok(nhanVienService.add(nhanVien));
    }
    @PutMapping("")
    public ResponseEntity<?> update(@Valid @RequestBody NhanVienDtoRequest nhanVien,
                                 BindingResult result) {
        if(nhanVien.getUsername()!=null && !nhanVien.getUsername().isBlank()){
            if(!nhanVienService.existsByUsername(nhanVien.getUsername())){
                result.addError(new FieldError("username","username","Username Không tồn tại"));
                if(!result.hasErrors()) return ValidateUtil.getErrors(result);
            }
        }
        if(result.hasErrors()) return ValidateUtil.getErrors(result);
        return ResponseEntity.ok(nhanVienService.update(nhanVien));
    }

    @GetMapping("getUser")
    public ResponseEntity<?> getUserAdmin(Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(nhanVienService.findById(username));
    }

}
