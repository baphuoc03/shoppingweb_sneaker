package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.reponse.NhanVienDtoResponse;
import com.example.shoppingweb.dto.request.NhanVienDtoRequest;
import com.example.shoppingweb.model.NhanVienModel;
import com.example.shoppingweb.repository.INhanVienRepository;
import com.example.shoppingweb.service.INhanVienService;
import com.example.shoppingweb.util.EmailUtil;
import com.example.shoppingweb.util.ImgUtil;
import com.example.shoppingweb.util.RandomUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.PageRanges;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class NhanVienServiceImpl implements INhanVienService {

    @Autowired
    private INhanVienRepository nhanVienRepository;

    @Override
    public Page<NhanVienDtoResponse> getAll(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page,limit);
        Page<NhanVienModel> pageModel = nhanVienRepository.findAll(pageable);
        return new PageImpl<>(pageModel.getContent().stream().map(n -> new NhanVienDtoResponse(n)).collect(Collectors.toList()),
                                pageable,pageModel.getTotalElements());
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
    public NhanVienDtoResponse update(NhanVienDtoRequest nhanVien) {
        NhanVienModel nhanVienDefault = nhanVienRepository.findById(nhanVien.getUsername()).get();
        nhanVien.setPassword(nhanVienDefault.getPassword());
        nhanVien.setVaiTro(nhanVienDefault.getVaiTro().getMa());
        nhanVien.setAnhDaiDien(nhanVienDefault.getAnhDaiDien());

        NhanVienModel nhanVienModel = nhanVienRepository.save(nhanVien.mapToModel());
        return new NhanVienDtoResponse(nhanVienModel);
    }

    @Override
    public NhanVienDtoResponse update(NhanVienDtoRequest nhanVien, MultipartFile img) throws IOException {

        NhanVienModel nhanVienDefault = nhanVienRepository.findById(nhanVien.getUsername()).get();
        nhanVien.setPassword(nhanVienDefault.getPassword());
        nhanVien.setVaiTro(nhanVienDefault.getVaiTro().getMa());

        if(img==null) {
            if(nhanVienDefault.getAnhDaiDien()!=null) ImgUtil.deleteImg(nhanVienDefault.getAnhDaiDien(),"user");
            nhanVien.setAnhDaiDien(null);
        }else{
            if(!img.getOriginalFilename().equalsIgnoreCase(nhanVien.getAnhDaiDien())){//add ảnh
                byte[] bytes = img.getBytes();
                String fileName = img.getOriginalFilename();
                String name = nhanVien.getUsername() + fileName.substring(fileName.lastIndexOf("."));
                Path path = Paths.get("src/main/resources/images/user/" + name);
                Files.write(path, bytes);
                nhanVien.setAnhDaiDien(name);
            }else{
                nhanVien.setAnhDaiDien(img.getOriginalFilename());
            }
        }

        NhanVienModel nhanVienModel = nhanVienRepository.save(nhanVien.mapToModel());
        return new NhanVienDtoResponse(nhanVienModel);
    }

    @Override
    public void deleteByUsername(String username){
        nhanVienRepository.deleteById(username);
    }



}
