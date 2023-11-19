package com.example.shoppingweb.dto.request;

import com.example.shoppingweb.model.ChatLieuModel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatLieuDTORequest {
    private String id;
    @NotBlank(message = "Không để trống tên")
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
