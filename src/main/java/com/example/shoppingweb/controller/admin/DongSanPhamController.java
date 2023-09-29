package com.example.shoppingweb.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${admin.domain}/dong-san-pham")
public class DongSanPhamController {
    @GetMapping("")
    public String show(){
        return "/admin/dongSanPham";
    }
}
