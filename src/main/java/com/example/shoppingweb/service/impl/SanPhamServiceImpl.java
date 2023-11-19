package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.filter.SanPhamDtoFilter;
import com.example.shoppingweb.dto.reponse.SanPhamDtoResponse;
import com.example.shoppingweb.dto.request.SanPhamDtoRequest;
import com.example.shoppingweb.entitymanager.SanPhamEntityManager;
import com.example.shoppingweb.model.AnhModel;
import com.example.shoppingweb.model.SanPhamModel;
import com.example.shoppingweb.repository.IDongSanPhamRepository;
import com.example.shoppingweb.repository.ISanPhamRepository;
import com.example.shoppingweb.service.ISanPhamService;
import com.example.shoppingweb.util.ImgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SanPhamServiceImpl implements ISanPhamService {
    @Autowired
    private ISanPhamRepository sanPhamRepository;

    @Autowired
    private AnhServiceImpl anhService;
    @Autowired
    private SanPhamEntityManager sanPhamEntityManager;
    @Autowired
    private IDongSanPhamRepository dongSanPhamRepository;

    @Override
    public List<SanPhamDtoResponse> findAll() {
        return sanPhamRepository.findAll().stream()
                .filter(s -> s.getTrangThai() == true)
                .map(s -> new SanPhamDtoResponse(s))
                .collect(Collectors.toList());
    }

    @Override
    public Page<SanPhamDtoResponse> pagination(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        List<SanPhamDtoResponse> pageContent = sanPhamRepository.findAll().stream()
                .filter(s -> s.getTrangThai() == true)
                .map(s -> new SanPhamDtoResponse(s))
                .collect(Collectors.toList());
        Page<SanPhamDtoResponse> pageDto = new PageImpl<>(pageContent.stream().skip(pageable.getOffset()).limit(limit).collect(Collectors.toList())
                , pageable, pageContent.size());
        return pageDto;
    }

    @Override
    public Page<SanPhamDtoResponse> paginationInUser(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        List<SanPhamDtoResponse> pageContent = sanPhamRepository.findAll().stream()
                .filter(s -> s.getTrangThai() == true)
                .filter(s -> s.getHienThi() == true)
                .map(s -> new SanPhamDtoResponse(s))
                .collect(Collectors.toList());
        Page<SanPhamDtoResponse> pageDto = new PageImpl<>(pageContent.stream().skip(pageable.getOffset()).limit(limit).collect(Collectors.toList())
                , pageable, pageContent.size());
        return pageDto;
    }

    @Override
    public Page<SanPhamDtoResponse> paginationInUserByThuongHieu(Integer page, Integer limit,String idThuongHieu) {
        Pageable pageable = PageRequest.of(page, limit);
        List<SanPhamDtoResponse> pageContent = sanPhamRepository.findAll().stream()
                .filter(s -> s.getTrangThai() == true)
                .filter(s -> s.getHienThi() == true)
                .filter(s -> s.getDongSanPham() != null)
                .filter(s -> s.getDongSanPham().getThuongHieu().getId().equals(idThuongHieu))
                .map(s -> new SanPhamDtoResponse(s))
                .collect(Collectors.toList());
        Page<SanPhamDtoResponse> pageDto = new PageImpl<>(pageContent.stream().skip(pageable.getOffset()).limit(limit).collect(Collectors.toList())
                , pageable, pageContent.size());
        return pageDto;
    }


    @Override
    public SanPhamDtoResponse findByMa(String ma) {
        SanPhamModel sanPhamModel = sanPhamRepository.findById(ma).get();
        return new SanPhamDtoResponse(sanPhamModel);
    }

    @Override
    public SanPhamDtoRequest findDtoRequetsByMa(String ma) {
        SanPhamModel sanPhamModel = sanPhamRepository.findById(ma).get();
        return new SanPhamDtoRequest(sanPhamModel);
    }

    @Override
    public List<SanPhamDtoResponse> saveAll(List<SanPhamDtoRequest> sanPham) {

        List<SanPhamModel> entities = sanPham.stream().map(s -> s.mapToModel()).collect(Collectors.toList());

        entities = sanPhamRepository.saveAll(entities);

        return entities.stream().map(s -> new SanPhamDtoResponse(s)).collect(Collectors.toList());
    }

    @Override
    public List<SanPhamModel> findByAllSanPhamWithKM() {
        return sanPhamRepository.findAllSanPhamWithKhuyenMai();
    }

    @Override
    public List<SanPhamModel> findAllWithKmWhereNgayBD() {
        return sanPhamRepository.findAllSanPhamWithKmWhereNgayBatDau();
    }

    @Override
    public SanPhamDtoResponse save(SanPhamDtoRequest entity) {
        SanPhamModel model = entity.mapToModel();
        model.setGiaNiemYet(entity.getGiaBan());
        List<AnhModel> imgs = model.getImages();
        model.setTrangThai(true);
        model = sanPhamRepository.save(model);
        anhService.saveAll(imgs);
        return new SanPhamDtoResponse(model);
    }

    @Override
    public SanPhamModel save1(SanPhamModel entity) {
        return sanPhamRepository.save(entity);
    }


    @Override
    public SanPhamDtoResponse update(SanPhamDtoRequest entity) throws IOException {
//        ImgUtil.deleteImg(findDtoRequetsByMa(entity.getMa()).getAnh(),"product");

        SanPhamModel model = entity.mapToModel();

        SanPhamModel sanPhamOld = sanPhamRepository.findById(model.getMa()).get();
        BigDecimal giamGia = sanPhamOld.getGiaBan().subtract(sanPhamOld.getGiaNiemYet());
        model.setGiaNiemYet(model.getGiaBan().subtract(giamGia));

        model.setNgayTao(sanPhamOld.getNgayTao());

        anhService.deleteBySanPham(model);
        anhService.saveAll(model.getImages());
        model.setTrangThai(true);
        model = sanPhamRepository.save(model);
        return new SanPhamDtoResponse(model);
    }

    @Override
    public boolean existsById(String s) {
        return sanPhamRepository.existsById(s);
    }

    @Override
    public void deleteById(String s) throws IOException {

        SanPhamModel model = sanPhamRepository.findById(s).get();
        Boolean checkCTSPInSanPham = model.getCtsp().stream().allMatch(c -> c.kiemTraCoTrongDonHang() == false);
        ImgUtil.deleteImg(model.getImages().stream().map(img -> img.getTen()).collect(Collectors.toList()), "product");
        if (model.getCtsp().size() == 0 || checkCTSPInSanPham == true) {
            anhService.deleteBySanPham(model);
            sanPhamRepository.deleteById(s);
        } else {
            model.setTrangThai(false);
            sanPhamRepository.save(model);
        }
//        anhService.deleteBySanPham(model);

    }

    @Override
    public Integer updateTrangThaiHIenThi(Boolean trangThai, String ma) {
        return sanPhamRepository.updateTrangThaiHIenThi(trangThai, ma);
    }

    @Override
    public Page<SanPhamDtoResponse> filter(SanPhamDtoFilter sanPhamDtoFilter, Integer pageNumber, Integer limt) {
        return sanPhamEntityManager.filterMultipleProperties(sanPhamDtoFilter, pageNumber, limt);
    }

    @Override
    public Page<SanPhamDtoResponse> filterInUser(SanPhamDtoFilter sanPhamDtoFilter, Integer pageNumber, Integer limt) {
        return sanPhamEntityManager.filterMultiplePropertiesInUser(sanPhamDtoFilter, pageNumber, limt);
    }

    public Integer updateGiaBan(BigDecimal giaBan, String ma) {
        return sanPhamRepository.updateGiaBan(giaBan, ma);
    }

    @Override
    public List<SanPhamDtoResponse> getSanPhamTuongTu(String ma) {
        List<SanPhamDtoResponse> listSP = new ArrayList<>();
        SanPhamModel sanPhamModel = sanPhamRepository.findById(ma).orElse(null);

        if (sanPhamModel == null || sanPhamModel.getDongSanPham() == null) return listSP;

        listSP = dongSanPhamRepository.findById(sanPhamModel.getDongSanPham().getId()).get().getDanhSachSanPham()
                .stream()
//                .filter(s -> !s.getMa().equals(ma))
                .map(s -> new SanPhamDtoResponse(s)).collect(Collectors.toList());
        return listSP;
    }

    @Override
    public List<SanPhamDtoResponse> getBanChay(Integer limit) {
        List<SanPhamDtoResponse> lst = sanPhamRepository.getBanChay(PageRequest.of(0, limit))
                .getContent().stream()
                .map(s -> new SanPhamDtoResponse(s)).collect(Collectors.toList());
        return lst;
    }

    @Override
    public List<SanPhamDtoResponse> getKhuyenMai(Integer limit) {
        List<SanPhamDtoResponse> lst = sanPhamRepository.getKhuyenMai(PageRequest.of(0, limit))
                .getContent().stream()
                .map(s -> new SanPhamDtoResponse(s)).collect(Collectors.toList());
        return lst;
    }

    @Override
    public List<SanPhamDtoResponse> getSanPhamMoi(Integer limit) {
        List<SanPhamDtoResponse> lst = sanPhamRepository.getSanPhamMoi(PageRequest.of(0, limit))
                .getContent().stream()
                .map(s -> new SanPhamDtoResponse(s)).collect(Collectors.toList());

        return lst;
    }
}
