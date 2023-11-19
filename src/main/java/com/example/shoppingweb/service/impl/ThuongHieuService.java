package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.reponse.ThuongHieuDtoResponse;
import com.example.shoppingweb.dto.request.ThuongHieuDtoRequest;
import com.example.shoppingweb.model.ThuongHieuModel;
import com.example.shoppingweb.repository.IThuongHieuRepository;
import com.example.shoppingweb.service.IThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThuongHieuService implements IThuongHieuService {
    @Autowired
    private IThuongHieuRepository iThuongHieuRepository;
    @Override
    public List<ThuongHieuDtoResponse> findAll() {
        return iThuongHieuRepository.findAll().stream()
                .map(m-> new ThuongHieuDtoResponse(m))
                .collect(Collectors.toList());
    }

    @Override
    public ThuongHieuDtoResponse save(ThuongHieuDtoRequest thuongHieuDtoRequest) {
        ThuongHieuModel model = iThuongHieuRepository.save(thuongHieuDtoRequest.mapToModel());
        return new ThuongHieuDtoResponse(model);
    }

    @Override
    public ThuongHieuDtoResponse findById(String s) {
        ThuongHieuModel model = iThuongHieuRepository.findById(s).get();
        return new ThuongHieuDtoResponse(model);
    }

    @Override
    public boolean existsById(String s) {
        return iThuongHieuRepository.existsById(s);
    }

    @Override
    public void deleteById(String s) {
    iThuongHieuRepository.deleteById(s);
    }

    @Override
    public void deleteByIds(List<String> s) {
    for (String id : s){
        iThuongHieuRepository.deleteById(id);
    }
    }
}
