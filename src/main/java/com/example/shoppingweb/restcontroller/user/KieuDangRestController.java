package com.example.shoppingweb.restcontroller.user;

import com.example.shoppingweb.dto.reponse.KieuDangDTOResponse;
import com.example.shoppingweb.service.IKieuDangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("kieu-dang-user")
@RequestMapping("kieu-dang")
public class KieuDangRestController {
    @Autowired
    private IKieuDangService service;

    @GetMapping("find-all")
    public List<KieuDangDTOResponse> findAll() {
        return service.getAll();
    }
}
