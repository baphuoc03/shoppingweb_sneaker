package com.example.shoppingweb.controller.admin;

import com.example.shoppingweb.dto.reponse.*;
import com.example.shoppingweb.dto.request.SanPhamDtoRequest;
import com.example.shoppingweb.service.*;
import com.example.shoppingweb.service.impl.AnhServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("${admin.domain}/san-pham")
public class SanPhamController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISanPhamService sanPhamService;

    @Autowired
    private IMauSacService mauSacService;
    @Autowired
    private IDongSanPhamService dongSanPhamService;
    @Autowired
    private IChatLieuService chatLieuService;
    @Autowired
    private IKieuDangService kieuDangService;
    @Autowired
    private AnhServiceImpl anhService;
    @Autowired
    private IThuongHieuService thuongHieuService;
    @Autowired
    private IXuatXuService xuatXuService;


    @GetMapping("")
    public String hienThi() {
        return "/admin/sanPham";
    }

    @GetMapping("add")
    public String viewAdd(@ModelAttribute("sanPham") SanPhamDtoRequest sanPham) {
//        request.setAttribute("sanPham", new SanPhamDtoRequest());
        request.setAttribute("method","add");
        request.setAttribute("action", "add");
        return "/admin/formSanPham";
    }

    @PostMapping("add")
    public String add(@Valid @ModelAttribute("sanPham") SanPhamDtoRequest sanPham, BindingResult result,
                      @RequestParam(value = "pro-image",required = false) List<MultipartFile> file) throws IOException {


        if (result.hasErrors()) {
            request.setAttribute("method","add");
            request.setAttribute("action", "add");
            return "admin/formSanPham";
        }


//        String tenSanPham = sanPham.getMa() + " - " +sanPham.getTen();
//        ThongBaoModel thongBao = new ThongBaoModel(null,null, ThongBaoType.Add.name(),"Thêm mới sản phẩm: "+tenSanPham,new Date(),null);
//        SocketUtil.sendNotification(thongBao);

        sanPham.setAnh(file);
        sanPham.setNgayCapNhat(new Date());
        sanPhamService.save(sanPham);
        return "redirect:/admin/san-pham";
    }

    @GetMapping("update/{ma}")
    public String viewUpdate(Model model,
                             @PathVariable("ma")String ma) {
//        request.setAttribute("sanPham", new SanPhamDtoRequest());
        model.addAttribute("sanPham" , sanPhamService.findDtoRequetsByMa(ma));
        request.setAttribute("method","put");
        request.setAttribute("action", "update/"+ma);
        return "/admin/formSanPham";
    }
    @PutMapping("update/{ma}")
    public String update(@Valid @ModelAttribute("sanPham") SanPhamDtoRequest sanPham, BindingResult result,
                         @PathVariable("ma")String ma,
                         @RequestParam(value = "pro-image",required = false) List<MultipartFile> file) throws IOException {

        if (result.hasErrors()) {
            request.setAttribute("method","put");
            request.setAttribute("action", "update/"+ma);
            return "admin/formSanPham";
        }

        sanPham.setMa(ma);
        sanPham.setAnh(file,anhService.findAllBySanPham(sanPham.mapToModel()).stream().map(img -> img.getTen()).collect(Collectors.toSet()));
        sanPhamService.update(sanPham);

        return "redirect:/admin/san-pham";
    }


    @ModelAttribute("colors")
    public List<MauSacDTOResponse> getMauSac(){
        return mauSacService.findAll();
    }

    @ModelAttribute("dongSanPham")
    public List<DongSanPhamResponese> getDongSanPham(){
        return dongSanPhamService.findAll();
    }

    @ModelAttribute("chatLieu")
    public List<ChatLieuDTOResponse> getChatLieu(){
        return chatLieuService.findAll();
    }

    @ModelAttribute("kieuDang")
    public List<KieuDangDTOResponse> getKieuDang(){
        return kieuDangService.getAll();
    }
    @ModelAttribute("thuongHieu")
    public List<ThuongHieuDtoResponse> getThuongHieu(){
        return thuongHieuService.findAll();
    }
    @ModelAttribute("xuatXu")
    private List<XuatXuResponse> getXuatXu(){
        return xuatXuService.findAll();
    }


}
