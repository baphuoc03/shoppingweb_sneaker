package com.example.shoppingweb.restcontroller.admin;

import com.example.shoppingweb.dto.reponse.SizeDTOResponse;
import com.example.shoppingweb.dto.request.SizeDTORequest;
import com.example.shoppingweb.service.ISizeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${admin.domain}/size")
@CrossOrigin(origins = "*")
public class SizeRestontroller {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISizeService service;

    @GetMapping("/find-all")
    public List<SizeDTOResponse> findAll() {
        return service.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody SizeDTORequest size) throws IOException {
        System.out.println(size.toString());
        return ResponseEntity.ok(service.save(size));
    }

    @GetMapping("/chi-tiet/{ma}")
    public ResponseEntity<SizeDTOResponse> chiTiet(@PathVariable("ma") Float ma){
        System.out.println(ma);
        return ResponseEntity.ok(service.findById(ma));
    }

    @DeleteMapping("/delete/{ma}")
    public ResponseEntity<?> delete(@PathVariable Float ma) {
        service.deleteById(ma);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{ma}")
    public ResponseEntity<?> update(@RequestBody SizeDTORequest size, @PathVariable Float ma) {
        size.setMa(ma);
        return ResponseEntity.ok(service.save(size));
    }
}
