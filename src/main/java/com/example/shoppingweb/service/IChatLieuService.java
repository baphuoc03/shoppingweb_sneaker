package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.ChatLieuDTOResponse;
import com.example.shoppingweb.dto.request.ChatLieuDTORequest;

import java.util.List;

public interface IChatLieuService {
    List<ChatLieuDTOResponse> findAll();

    ChatLieuDTOResponse save(ChatLieuDTORequest chatLieuDTORequest);

    ChatLieuDTOResponse findById(String s);

    boolean existsById(String s);

    void deleteById(String s);
}
