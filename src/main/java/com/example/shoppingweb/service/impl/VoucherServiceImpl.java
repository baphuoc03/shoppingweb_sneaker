package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.reponse.VoucherReponse;
import com.example.shoppingweb.dto.request.VoucherRequest;
import com.example.shoppingweb.model.VoucherModel;
import com.example.shoppingweb.repository.VoucherRepository;
import com.example.shoppingweb.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepository repository;


    @Override
    public Page<VoucherReponse> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<VoucherModel> pageModel = repository.findAll(pageable);
        return pageModel.map(x -> new VoucherReponse(x));
    }

    @Override
    public Page<VoucherReponse> findByName(String keyword, Pageable pageable) {
        Page<VoucherModel> page = repository.findByTenLike(keyword, pageable);
        return page.map(x -> new VoucherReponse(x));
    }

    @Override
    public VoucherReponse findById(String id) {
        VoucherModel getById = repository.findById(id).get();
        return new VoucherReponse(getById);
    }

    @Override
    public VoucherReponse addVoucher(VoucherRequest voucher) {
        VoucherModel voucherModel = repository.save(voucher.maptoModel());
        return new VoucherReponse(voucherModel);
    }

    @Override
    public void deleteVoucher(String id) {
        repository.deleteById(id);
    }

    @Override
    public boolean exitst(String id) {
        boolean exitst = repository.existsById(id);
        return exitst;
    }

    @Override
    public void deleteVouchers(List<String> ids) {
        for (String id : ids) {
            repository.deleteById(id);
        }
    }
}
