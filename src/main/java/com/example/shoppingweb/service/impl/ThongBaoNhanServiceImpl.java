package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.model.ThongBaoNhanModel;
import com.example.shoppingweb.repository.IThongBaoNhanRepository;
import com.example.shoppingweb.service.IThongBaoNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThongBaoNhanServiceImpl implements IThongBaoNhanService {
    @Autowired
    private IThongBaoNhanRepository thongBaoNhanRepository;

    @Override
    public List<ThongBaoNhanModel> saveAll(List<ThongBaoNhanModel> thongBaoNhanModel) {
        return thongBaoNhanRepository.saveAll(thongBaoNhanModel);
    }

    @Override
    public ThongBaoNhanModel save(ThongBaoNhanModel thongBaoNhanModel) {
        return thongBaoNhanRepository.save(thongBaoNhanModel);
    }

    @Override
    public List<ThongBaoNhanModel> getAllByNguoiNhanId(String idNguoiNhan) {
        return thongBaoNhanRepository.getAllByNguoiNhanIdOrderByThongBaoGuiDesc(idNguoiNhan);
    }
}
