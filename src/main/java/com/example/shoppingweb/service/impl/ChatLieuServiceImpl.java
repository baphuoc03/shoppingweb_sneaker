package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.reponse.ChatLieuDTOResponse;
import com.example.shoppingweb.dto.request.ChatLieuDTORequest;
import com.example.shoppingweb.model.ChatLieuModel;
import com.example.shoppingweb.repository.IChatLieuRepository;
import com.example.shoppingweb.service.IChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatLieuServiceImpl implements IChatLieuService {
    @Autowired
    private IChatLieuRepository chatLieuRepository;

    public List<ChatLieuDTOResponse> findAll() {
        return chatLieuRepository.findAll().stream()
                .map(m -> new ChatLieuDTOResponse(m))
                .collect(Collectors.toList());
    }

    public ChatLieuDTOResponse save(ChatLieuDTORequest chatLieuDTORequest) {
        ChatLieuModel model = chatLieuRepository.save(chatLieuDTORequest.mapToModel());
        return new ChatLieuDTOResponse(model);
    }

    public ChatLieuDTOResponse findById(String s) {
        ChatLieuModel model = chatLieuRepository.findById(s).get();
        return new ChatLieuDTOResponse(model);
    }

    public boolean existsById(String s) {
        return chatLieuRepository.existsById(s);
    }

    public void deleteById(String s) {
        chatLieuRepository.deleteById(s);
    }

}
