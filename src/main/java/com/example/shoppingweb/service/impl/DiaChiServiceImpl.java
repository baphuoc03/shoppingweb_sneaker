package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.reponse.DiaChiDTOResponse;
import com.example.shoppingweb.dto.request.DiaChiDTORequest;
import com.example.shoppingweb.model.DiaChiModel;
import com.example.shoppingweb.repository.IDiaChiRepository;
import com.example.shoppingweb.service.IDiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DiaChiServiceImpl implements IDiaChiService {
    @Autowired
    IDiaChiRepository repository;

    @Autowired
    IDiaChiRepository diaChiRepository;

    @Override
    public DiaChiModel findByIdModel(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void addDiaChi(DiaChiModel diaChiModel) {
        this. repository.save(diaChiModel);
    }

    @Override
    public Page<DiaChiDTOResponse> getAll(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<DiaChiModel> pageModel = diaChiRepository.findAll(pageable);

        return new PageImpl<>(pageModel.getContent().stream().map(k -> new DiaChiDTOResponse(k)).collect(Collectors.toList()),
        pageable, pageModel.getTotalElements());

    }

    @Override
    public DiaChiDTOResponse findById(Long id) {
        return new DiaChiDTOResponse(diaChiRepository.findById(id).get());
    }

    @Override
    public Boolean exsistsById(Long id) {
        return diaChiRepository.existsById(id);
    }

    @Override
    public DiaChiDTOResponse add(DiaChiDTORequest diaChi) throws MessagingException {
       DiaChiModel diaChiModel = diaChiRepository.save(diaChi.mapToModel());
        return new DiaChiDTOResponse(diaChiModel);
    }

    @Override
    public DiaChiDTOResponse update(DiaChiDTORequest diaChi) {
        DiaChiModel diaChiModel = diaChiRepository.save(diaChi.mapToModel());
        return new DiaChiDTOResponse(diaChiModel);
    }

    @Override
    public void deleteById(Long id) {
        diaChiRepository.deleteById(id);

    }

}
