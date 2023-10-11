package com.example.shoppingweb.service;

import com.example.shoppingweb.dto.reponse.VoucherReponse;
import com.example.shoppingweb.dto.request.VoucherRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VoucherService {

    public Page<VoucherReponse> findAll(int pageNumber, int pageSize);

    public Page<VoucherReponse> findByName(String keyword, Pageable pageable);

    public VoucherReponse findById(String id);

    public VoucherReponse addVoucher(VoucherRequest voucher);

    public void deleteVoucher(String id);

    public boolean exitst(String id);

    public void deleteVouchers(List<String> ids);

}
