package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.model.ThongBaoModel;
import com.example.shoppingweb.repository.IThongBaoRepository;
import com.example.shoppingweb.service.IThongBaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThongBaoServiceImpl implements IThongBaoService {
    @Autowired
    private IThongBaoRepository thongBaoRepository;

    @Override
    public ThongBaoModel save(ThongBaoModel thongBaoModel) {
        return thongBaoRepository.save(thongBaoModel);
    }
}
