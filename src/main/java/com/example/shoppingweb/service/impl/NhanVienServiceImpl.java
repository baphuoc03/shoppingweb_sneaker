package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.reponse.NhanVienDtoResponse;
import com.example.shoppingweb.dto.request.NhanVienDtoRequest;
import com.example.shoppingweb.model.NhanVienModel;
import com.example.shoppingweb.repository.INhanVienRepository;
import com.example.shoppingweb.service.INhanVienService;
import com.example.shoppingweb.util.EmailUtil;
import com.example.shoppingweb.util.RandomUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NhanVienServiceImpl implements INhanVienService {

    @Autowired
    private INhanVienRepository nhanVienRepository;

    @Override
    public List<NhanVienDtoResponse> getAll() {
        return nhanVienRepository.findAll().stream().map(n -> new NhanVienDtoResponse(n)).collect(Collectors.toList());
    }

    @Override
    public NhanVienDtoResponse findById(String username) {
        return new NhanVienDtoResponse(nhanVienRepository.findById(username).get());
    }

    @Override
    public Boolean existsByUsername(String username){
        return nhanVienRepository.existsById(username);
    }

    @Override
    public NhanVienDtoResponse add(NhanVienDtoRequest nhanVien) throws MessagingException {
        nhanVien.setPassword(RandomUtil.randomPassword());
        EmailUtil.sendEmail(nhanVien.getEmail(),"Thông Tin Tài Khoản","Thông tin tài khoản: \nUsername: "+nhanVien.getUsername()+"\nPassword: "+nhanVien.getPassword());
        NhanVienModel nhanVienModel = nhanVienRepository.save(nhanVien.mapToModel());
        return new NhanVienDtoResponse(nhanVienModel);
    }

    @Override
    public NhanVienDtoResponse update(NhanVienDtoRequest nhanVien)  {
        NhanVienModel nhanVienDefault = nhanVienRepository.findById(nhanVien.getUsername()).get();
        nhanVien.setPassword(nhanVienDefault.getPassword());
        nhanVien.setVaiTro(nhanVienDefault.getVaiTro().getMa());
        NhanVienModel nhanVienModel = nhanVienRepository.save(nhanVien.mapToModel());
        return new NhanVienDtoResponse(nhanVienModel);
    }

}
