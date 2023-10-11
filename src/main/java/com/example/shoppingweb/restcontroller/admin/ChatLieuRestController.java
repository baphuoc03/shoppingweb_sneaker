package com.example.shoppingweb.restcontroller.admin;

import com.example.shoppingweb.dto.reponse.ChatLieuDTOResponse;
import com.example.shoppingweb.dto.request.ChatLieuDTORequest;
import com.example.shoppingweb.service.IChatLieuService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${admin.domain}/chat-lieu")
public class ChatLieuRestController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IChatLieuService service;

    @GetMapping("find-all")
    public List<ChatLieuDTOResponse> findAll() {
        return service.findAll();
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody ChatLieuDTORequest chatLieu) throws IOException {
        return ResponseEntity.ok(service.save(chatLieu));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody ChatLieuDTORequest chatLieu, @PathVariable String id) {
        chatLieu.setId(id);
        return ResponseEntity.ok(service.save(chatLieu));
    }
    @GetMapping("/chi-tiet/{id}")
    public ResponseEntity<ChatLieuDTOResponse> chiTiet(@PathVariable("id") String id){
        System.out.println(id);
        return ResponseEntity.ok(service.findById(id));
    }
}
