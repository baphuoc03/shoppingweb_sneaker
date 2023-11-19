package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.reponse.ChiTietDonHangDtoResponse;
import com.example.shoppingweb.dto.reponse.DonHangDtoResponse;
import com.example.shoppingweb.dto.reponse.DonHangReponseUser;
import com.example.shoppingweb.dto.request.ChiTietDonHangDTORequest;
import com.example.shoppingweb.dto.request.DonHangDTORequest;
import com.example.shoppingweb.model.ChiTietDonHangModel;
import com.example.shoppingweb.model.ChiTietSanPhamModel;
import com.example.shoppingweb.model.DonHangModel;
import com.example.shoppingweb.repository.IChiTietDonHangRepository;
import com.example.shoppingweb.repository.IChiTietSanPhamRepository;
import com.example.shoppingweb.repository.IDonHangResponsitory;
import com.example.shoppingweb.service.IChiTietDonHangService;
import com.example.shoppingweb.service.IDonHangService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonHangService implements IDonHangService {

    @Autowired
    private IDonHangResponsitory donHangResponsitory;
    @Autowired
    private IChiTietDonHangService chiTietDonHangService;
    @Autowired
    private IChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    private IChiTietDonHangRepository chiTietDonHangRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public List<DonHangDtoResponse> getAllByTrangThai(Integer trangThai) {
        return null;
    }

    @Override
    public List<DonHangReponseUser> getAllByKhachHangAndTrangThai(String nguoiSoHuu, Integer trangThai) {

        return donHangResponsitory.findAllByKhachHangAndTrangThai(nguoiSoHuu, trangThai).stream().map(d -> new DonHangReponseUser(d)).collect(Collectors.toList());

    }


    @Override
    public Page<DonHangDtoResponse> getAllByTrangThai(Integer trangThai, Integer limit, Integer pageNumber) {

        Pageable pageable = PageRequest.of(pageNumber, limit);

        Page<DonHangModel> pageModel = donHangResponsitory.getAllByTrangThai(trangThai, pageable);

        return new PageImpl<>(pageModel.getContent().stream().map(d -> new DonHangDtoResponse(d)).collect(Collectors.toList()),
                pageable, pageModel.getTotalElements());
    }

    @Override
    public DonHangDtoResponse checkOut(DonHangDTORequest donHangDTORequest) {
        DonHangModel model = donHangDTORequest.mapModel();
        donHangResponsitory.saveAndFlush(model);
        return new DonHangDtoResponse(donHangResponsitory.findById(model.getMa()).get());
    }

    @Override
    public DonHangDtoResponse findByMa(String ma) {
        return new DonHangDtoResponse(donHangResponsitory.findById(ma).orElse(new DonHangModel()));
    }

    @Override
    public DonHangReponseUser findByMaUser(String ma) {
        return new DonHangReponseUser((donHangResponsitory.findById(ma).orElse(new DonHangModel())));
    }

    @Override
    public Boolean existsByMa(String ma) {
        return donHangResponsitory.existsById(ma);
    }

    @Override
    public void updateTrangThai(String maDonHang, Integer trangThai) throws MessagingException {
//         donHangResponsitory.updateTrangThaiDonHang(trangThai,maDonHang);
        DonHangModel model = donHangResponsitory.findById(maDonHang).get();
        model.setTrangThai(trangThai);

        String subject = "";
        String messeger = "";
        String title = "";
        if (trangThai == 2) {
            subject = "Tạo đơn hàng!";
            title = "Tạo đơn hàng thành công";
            messeger = "Xin chào " + model.getTenNguoiNhan() + ", đơn hàng của bạn đã được tạo. Cảm ơn bạn đã mua hàng";
        } else if (trangThai == 1) {
            subject = "Xác nhận đơn hàng!";
            title = "Xác nhận hàng thành công";
            model.setNgayXacNhan(new Date());
            messeger = "Xin chào " + model.getTenNguoiNhan() + ", đơn hàng của bạn đã được xác nhận. Cảm ơn bạn đã mua hàng. Đơn hàng đang được đóng gói và sẽ đến tay bạn trong vài ngày tới";
        } else if (trangThai == 3) {
            subject = "Chuyển giao đơn hàng!";
            title = "Đơn hàng đang được giao";
            model.setNgayGiaoHang(new Date());
            messeger = "Xin chào " + model.getTenNguoiNhan() + ", đơn hàng của bạn đang được giao. Cảm ơn bạn đã mua hàng. Đơn hàng đang được giao và sẽ đến tay bạn trong vài ngày tới";
        } else if (trangThai == 4) {
            subject = "Hoàn thành đơn hàng!";
            title = "Đơn hàng đã giao thành công";
            model.setNgayHoanThanh(new Date());
            messeger = "Xin chào " + model.getTenNguoiNhan() + ", đơn hàng của bạn được giao thành công. Cảm ơn bạn đã mua hàng.";
        }


        List<ChiTietDonHangDtoResponse> lstSanPham = chiTietDonHangService.getByDonHang(maDonHang);
        BigDecimal tongTien = BigDecimal.valueOf(0);
        for (ChiTietDonHangDtoResponse d : lstSanPham) {
            tongTien = tongTien.add(d.getDonGiaSauGiam().multiply(BigDecimal.valueOf(d.getSoLuong())));
        }

        Context context = new Context();
        context.setVariable("donHang", new DonHangDtoResponse(model));
        context.setVariable("products", lstSanPham);
        context.setVariable("totalPrice", tongTien);
        context.setVariable("mess", messeger);
        context.setVariable("title", title);
        String finalSubject = subject;
        new Thread(() -> {
            try {
                sendEmailDonHang(model.getEmail(), finalSubject, "email/capNhatTrangThaiDonHang", context, lstSanPham);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }).start();


        donHangResponsitory.saveAndFlush(model);
    }

    @Override
    public void huyDonHang(List<String> maDonHang, String lyDo) throws MessagingException {
//         donHangResponsitory.updateTrangThaiDonHang(trangThai,maDonHang);
        maDonHang.forEach(ma -> {
            DonHangModel model = donHangResponsitory.findById(ma).get();
            model.setLyDoHuy(lyDo);
            model.setTrangThai(0);
            model.setNgayHuy(new Date());

            String subject = "Hủy đơn hàng!";
            String messeger = "Xin chào " + model.getTenNguoiNhan() + ", đơn hàng của bạn đã hủy. Cảm ơn bạn đã ghé qua cửa hàng";

            List<ChiTietDonHangModel> ctdhModel = chiTietDonHangRepository.findAllByDonHang(model);
            ctdhModel.forEach(c -> {
                int soLuongInDonHang = c.getSoLuong();
                ChiTietSanPhamModel sanPhamInDonHang = chiTietSanPhamRepository.findById(c.getChiTietSanPham().getId()).get();
                sanPhamInDonHang.setSoLuong(soLuongInDonHang + sanPhamInDonHang.getSoLuong());
                chiTietSanPhamRepository.save(sanPhamInDonHang);
            });


            List<ChiTietDonHangDtoResponse> lstSanPham = ctdhModel.stream().map(m -> new ChiTietDonHangDtoResponse(m)).collect(Collectors.toList());
            BigDecimal tongTien = BigDecimal.valueOf(0);
            for (ChiTietDonHangDtoResponse d : lstSanPham) {
                tongTien = tongTien.add(d.getDonGiaSauGiam().multiply(BigDecimal.valueOf(d.getSoLuong())));
            }

            Context context = new Context();
            context.setVariable("donHang", new DonHangDtoResponse(model));
            context.setVariable("products", lstSanPham);
            context.setVariable("totalPrice", tongTien);
            context.setVariable("mess", messeger);
            context.setVariable("title", subject);
            context.setVariable("lyDo", lyDo);
            new Thread(() -> {
                try {
                    sendEmailDonHang(model.getEmail(), subject, "email/capNhatTrangThaiDonHang", context, lstSanPham);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }).start();

            donHangResponsitory.saveAndFlush(model);
        });
    }

    @Override
    public void huyDonHangUser(String maDonHang, String lyDo) throws MessagingException {
        DonHangModel model = donHangResponsitory.findById(maDonHang).get();
        model.setNgayHuy(new Date());
        model.setLyDoHuy(lyDo);
        model.setTrangThai(0);
        donHangResponsitory.save(model);
    }

    @Override
    public DonHangDtoResponse updateDonHang(DonHangDTORequest request, List<ChiTietDonHangDTORequest> products) {
        DonHangModel donHangOld = donHangResponsitory.findById(request.getMa()).orElse(null);
        DonHangModel model = request.mapModel();
        model.setPhuongThucThanhToan(donHangOld.getPhuongThucThanhToan());

        List<String> maCTSPNew = products.stream().map(c -> c.getId()).collect(Collectors.toList());
        List<ChiTietDonHangModel> ctdhModelOld = chiTietDonHangRepository.findAllByDonHang(model);
        ctdhModelOld.forEach(c -> {
            if (!maCTSPNew.contains(c.getId())) {
                //Thêm lại số lượng khi xóa sản phẩm khỏi đơn hàng
                ChiTietSanPhamModel chiTietSanPhamModel = chiTietSanPhamRepository.findById(c.getChiTietSanPham().getId()).get();
                chiTietSanPhamModel.setSoLuong(chiTietSanPhamModel.getSoLuong() + c.getSoLuong());
                chiTietSanPhamRepository.saveAndFlush(chiTietSanPhamModel);

                chiTietDonHangRepository.deleteById(c.getId());//xóa khỏi đơn hàng
            }
        });

        products.forEach(p -> {
            if (p.getId() != null) {
                ChiTietDonHangModel chiTietDHOld = chiTietDonHangRepository.findById(p.getId()).get();
                ChiTietSanPhamModel chiTietSanPhamModel = chiTietSanPhamRepository.findById(p.getSanPhamCT()).get();
                Long soLuong = chiTietSanPhamModel.getSoLuong() - (p.getSoLuong() - chiTietDHOld.getSoLuong());
                chiTietSanPhamModel.setSoLuong(soLuong);
                chiTietSanPhamRepository.saveAndFlush(chiTietSanPhamModel);
            } else {
                ChiTietSanPhamModel chiTietSanPhamModel = chiTietSanPhamRepository.findById(p.getSanPhamCT()).get();
                Long soLuong = chiTietSanPhamModel.getSoLuong() - p.getSoLuong();
                chiTietSanPhamModel.setSoLuong(soLuong);
                chiTietSanPhamRepository.saveAndFlush(chiTietSanPhamModel);
            }
        });

        products.forEach(p -> chiTietDonHangRepository.saveAndFlush(p.mapModel()));

        if (donHangOld.getVoucher() != null) {
            model.setVoucher(donHangOld.getVoucher());
        }
        if (donHangOld.getNguoiSoHuu() != null) {
            model.setNguoiSoHuu(donHangOld.getNguoiSoHuu());
        } else {
            model.setNguoiSoHuu(null);
        }


        String subject = "Cập nhật thông tin đơn hàng!";
        String messeger = "Xin chào " + model.getTenNguoiNhan() + ", đơn hàng của bạn vừa cập nhật thông tin!";

//        List<ChiTietDonHangModel> ctdhModel = chiTietDonHangRepository.findAllByDonHang(model);
//        ctdhModel.forEach(c -> {
//            int soLuongInDonHang = c.getSoLuong();
//            ChiTietSanPhamModel sanPhamInDonHang = chiTietSanPhamRepository.findById(c.getChiTietSanPham().getId()).get();
//            sanPhamInDonHang.setSoLuong(soLuongInDonHang+sanPhamInDonHang.getSoLuong());
//            chiTietSanPhamRepository.save(sanPhamInDonHang);
//        });


        chiTietDonHangRepository.findAllByDonHang(model).forEach(c -> {
            c.setChiTietSanPham(chiTietSanPhamRepository.findById(c.getChiTietSanPham().getId()).get());
        });
        List<ChiTietDonHangDtoResponse> lstSanPham = chiTietDonHangRepository.findAllByDonHang(model).stream().map(m -> new ChiTietDonHangDtoResponse(m)).collect(Collectors.toList());
        BigDecimal tongTien = BigDecimal.valueOf(0);
        for (ChiTietDonHangDtoResponse d : lstSanPham) {
            tongTien = tongTien.add(d.getDonGiaSauGiam().multiply(BigDecimal.valueOf(d.getSoLuong())));
        }

        Context context = new Context();
        context.setVariable("donHang", new DonHangDtoResponse(model));
        context.setVariable("products", lstSanPham);
        context.setVariable("totalPrice", tongTien);
        context.setVariable("mess", messeger);
        context.setVariable("title", subject);
        new Thread(() -> {
            try {
                sendEmailDonHang(model.getEmail(), subject, "email/capNhatTrangThaiDonHang", context, lstSanPham);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }).start();

        return new DonHangDtoResponse(donHangResponsitory.save(model));
    }

    public void sendEmailDonHang(String email, String subject, String tempalteHtml, Context context, List<ChiTietDonHangDtoResponse> lstSanPham) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        helper.setTo(email);
        helper.setSubject(subject);
        String htmlContent = templateEngine.process(tempalteHtml, context);
        helper.setText(htmlContent, true);

        ClassPathResource resource = new ClassPathResource("./images/product/default.png");
        helper.addInline("logo", resource);

        lstSanPham.forEach(s -> {
            ClassPathResource img = new ClassPathResource("./images/product/" + s.getAnh());
            try {
                helper.addInline(s.getAnh() + "", img);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });

        javaMailSender.send(mimeMessage);
    }

    @Override
    public Long getTotalQauntityInOrdersWithDate(Date firstDate, Date lastDate) {
        return donHangResponsitory.getTotalQauntityInOrdersWithDate(firstDate, lastDate) == null ? 0L : donHangResponsitory.getTotalQauntityInOrdersWithDate(firstDate, lastDate);
    }

    @Override
    public Long getQuantityOrdersWithDate(Date firstDate, Date lastDate) {
        return donHangResponsitory.getQuantityOrdersWithDate(firstDate, lastDate) == null ? 0L : donHangResponsitory.getQuantityOrdersWithDate(firstDate, lastDate);
    }

    @Override
    public BigDecimal getTotalPriceInOrdersWithDate(Date firstDate, Date lastDate) {
        return donHangResponsitory.getTotalPriceInOrdersWithDate(firstDate, lastDate) == null ? BigDecimal.valueOf(0) : donHangResponsitory.getTotalPriceInOrdersWithDate(firstDate, lastDate);
    }
}
