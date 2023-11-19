package com.example.shoppingweb.service.impl;
import com.example.shoppingweb.dto.reponse.KhuyenMaiResponse;
import com.example.shoppingweb.dto.request.KhuyenMaiRequest;
import com.example.shoppingweb.model.KhuyenMaiModel;
import com.example.shoppingweb.model.SanPhamModel;
import com.example.shoppingweb.repository.ISanPhamRepository;
import com.example.shoppingweb.repository.KhuyenMaiRepository;
import com.example.shoppingweb.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {
    @Autowired
    KhuyenMaiRepository repository;
    @Autowired
    ISanPhamRepository sanPhamRepository;

    @Override
    public Page<KhuyenMaiResponse> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<KhuyenMaiModel> pageModel = repository.findAll(pageable);
        return pageModel.map(x -> new KhuyenMaiResponse(x));
    }


    @Override
    public KhuyenMaiResponse findById(String id) {
        KhuyenMaiModel getById = repository.findById(id).get();
        return new KhuyenMaiResponse(getById);
    }

    @Override
    public void save(KhuyenMaiRequest khuyenMai) {
        repository.save(khuyenMai.mapToModel());
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void updateGiamGiaWithNgayBD() {
        for (var i : repository.findAllSanPhamWithKmWhereNgayBatDau()) {
            for (var j : i.getSanPham()) {
                BigDecimal giaUpdate = null;
                if (i.getLoai().equals("TIEN")) {
                    giaUpdate = j.getGiaNiemYet().subtract(i.getMucGiam());
                } else if (i.getLoai().equals("PHAN TRAM")) {
                    giaUpdate = j.getGiaNiemYet().subtract(j.getGiaNiemYet().multiply(i.getMucGiam().divide(new BigDecimal("100"))));
                }
                SanPhamModel sanPham = sanPhamRepository.findById(j.getMa()).get();
                sanPham.setMa(j.getMa());
                sanPham.setGiaBan(giaUpdate);
                sanPhamRepository.save(sanPham);
            }
        }
        System.out.println("Thành công");
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void updateGiamGiaWithNgayKT() {
        for (var i : repository.findAllSanPhamWithKmWhereNgayKetThuc()) {
            for (var j : i.getSanPham()) {
                SanPhamModel sanPham = sanPhamRepository.findById(j.getMa()).get();
                sanPham.setMa(j.getMa());
                sanPham.setGiaBan(sanPham.getGiaNiemYet());
                sanPhamRepository.save(sanPham);
            }
        }
        System.out.println("Thành công1");
    }
}
