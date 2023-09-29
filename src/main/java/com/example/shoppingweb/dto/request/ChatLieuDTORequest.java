package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.ChatLieuModel;
import lombok.Data;

import java.util.Date;

@Data
public class ChatLieuDTORequest {
    private String id;
    private String ten;
    private Date ngayTao;
    private Date ngayCapNhat;

    public ChatLieuModel mapToModel() {
        ChatLieuModel model = new ChatLieuModel();
        model.setId(id);
        model.setTen(ten);
        model.setNgayTao(ngayTao);
        model.setNgayCapNhat(ngayCapNhat);
        return model;
    }
}
