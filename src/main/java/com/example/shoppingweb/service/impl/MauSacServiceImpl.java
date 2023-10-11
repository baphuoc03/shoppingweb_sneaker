package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.reponse.MauSacDTOResponse;
import com.example.shoppingweb.dto.request.MauSacDTORequest;
import com.example.shoppingweb.model.MauSacModel;
import com.example.shoppingweb.repository.IMauSacRepository;
import com.example.shoppingweb.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MauSacServiceImpl implements IMauSacService {
    @Autowired
    private IMauSacRepository mauSacRepository;


    public List<MauSacDTOResponse> findAll() {
        return mauSacRepository.findAll().stream()
                .map(m -> new MauSacDTOResponse(m))
                .collect(Collectors.toList());
    }

    public MauSacDTOResponse save( MauSacDTORequest mauSacDTORequest) {

        MauSacModel model = mauSacRepository.save(mauSacDTORequest.mapToModel());
        return new MauSacDTOResponse(model);
    }

    public MauSacDTOResponse findById(String s) {
        MauSacModel model = mauSacRepository.findById(s).get();
        return new MauSacDTOResponse(model);
    }

    public boolean existsById(String s) {
        return mauSacRepository.existsById(s);
    }

    public void deleteById(String s) {
        mauSacRepository.deleteById(s);
    }
}
