package com.example.shoppingweb.restcontroller.admin;

import com.example.shoppingweb.dto.reponse.KieuDangDTOResponse;
import com.example.shoppingweb.dto.request.KieuDangDtoRequest;
import com.example.shoppingweb.service.IKieuDangService;
import com.example.shoppingweb.util.ValidateUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${admin.domain}/kieu-dang")
public class KieuDangRestController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IKieuDangService service;
    @GetMapping("find-all")
    public List<KieuDangDTOResponse> findAll(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") int number){
        Page<KieuDangDTOResponse> page = service.findAll(number-1, 5);
        List<KieuDangDTOResponse> list = page.getContent();
        return list;
    }
    @PostMapping("")
    public ResponseEntity<?> add(@Valid @RequestBody KieuDangDtoRequest kieudang, BindingResult result){
        if(result.hasErrors()){
            return ValidateUtil.getErrors(result);
        }
        return  ResponseEntity.ok(service.save(kieudang));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody KieuDangDtoRequest kieudang, @PathVariable("id") String id, BindingResult result){
            if(result.hasErrors()){
                return ValidateUtil.getErrors(result);
            }
            kieudang.setId(id);
            return ResponseEntity.ok(service.save(kieudang));
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> deletes(@RequestBody List<String> id){

        service.deleteByIds(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        if(!service.existsById(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public KieuDangDTOResponse findById(@PathVariable("id") String id){
        return service.findById(id);
    }
}
