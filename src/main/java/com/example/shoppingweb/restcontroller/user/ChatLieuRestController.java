package com.example.shoppingweb.restcontroller.user;

import com.example.shoppingweb.dto.reponse.ChatLieuDTOResponse;
import com.example.shoppingweb.service.IChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("chat-lieu-user")
@RequestMapping("chat-lieu")
public class ChatLieuRestController {
    @Autowired
    private IChatLieuService service;
    @GetMapping("find-all")
    public List<ChatLieuDTOResponse> findAll() {
        return service.findAll();
    }
}
