package com.example.shoppingweb.restcontroller.user;

import com.example.shoppingweb.dto.reponse.MauSacDTOResponse;
import com.example.shoppingweb.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("mau-sac-user")
@RequestMapping("mau-sac")
public class MauSacRestController {
    @Autowired
    private IMauSacService service;

    @GetMapping("find-all")
    public List<MauSacDTOResponse> findAll(){
        return service.findAll();
    }
}
