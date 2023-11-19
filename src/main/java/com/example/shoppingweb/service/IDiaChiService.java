package com.example.shoppingweb.service;


import com.example.shoppingweb.dto.reponse.DiaChiDTOResponse;
import com.example.shoppingweb.dto.request.DiaChiDTORequest;
import com.example.shoppingweb.model.DiaChiModel;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;

import java.util.List;
public interface IDiaChiService {
    DiaChiModel findByIdModel(Long id);
    void addDiaChi(DiaChiModel diaChiModel);
    Page<DiaChiDTOResponse> getAll(Integer page, Integer limit);
    DiaChiDTOResponse findById(Long id);
    Boolean exsistsById(Long id);
    DiaChiDTOResponse add(DiaChiDTORequest diaChi) throws MessagingException, MessagingException;
    DiaChiDTOResponse update(DiaChiDTORequest diaChi);
    void deleteById(Long id);
}
