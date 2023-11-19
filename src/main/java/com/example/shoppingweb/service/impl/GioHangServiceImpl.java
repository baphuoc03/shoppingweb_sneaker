package com.example.shoppingweb.service.impl;

import com.example.shoppingweb.dto.reponse.GioHangDtoReponse;
import com.example.shoppingweb.model.Cart;
import com.example.shoppingweb.repository.IChiTietSanPhamRepository;
import com.example.shoppingweb.repository.IGioHangRepository;
import com.example.shoppingweb.repository.IKhachHangRepository;
import com.example.shoppingweb.service.IGioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@SessionScope
public class GioHangServiceImpl implements IGioHangService {
    @Autowired
    IGioHangRepository repository;
    @Autowired
    private IKhachHangRepository khachHangRepository;
    @Autowired
    private IChiTietSanPhamRepository chiTietSanPhamRepository;

    private final Cart cart = new Cart();


    // update
    public void addOrUpdateToCart(String idCTSP,Integer sl){
        Map<String,Integer> sanPhamTrongGio  = cart.getProductInCart();

        boolean chk = false;
        //Nếu sản phẩm đã có trong giỏ thì + dồn số lượng
        if(sanPhamTrongGio.containsKey(idCTSP)){//Kiểm tra sản phẩm có trong giỏ hàng chưa
            //Lấy số lượng hiện tại
            Integer soLuongHienCo = sanPhamTrongGio.get(idCTSP);
            //Cộng số lượng
            Integer soLuongMoi = soLuongHienCo + sl;
            System.out.println("oke");
            System.out.println(soLuongMoi);
            //Cập nhật lại giỏ hàng
            sanPhamTrongGio.put(idCTSP,soLuongMoi);
        }else{
            sanPhamTrongGio.put(idCTSP,sl);
        }
        System.out.println(sanPhamTrongGio.toString());
        cart.setProductInCart(sanPhamTrongGio);
    }
    public void removeProductInCart(String idCTSP){
        Map<String,Integer> productInCart = cart.getProductInCart();
        productInCart.remove(idCTSP);
    }
    public void removeAllProdcutInCart(){
        Map<String,Integer> productInCart = cart.getProductInCart();
        productInCart.clear();
    }

    public void updateSoLuong(String key,Integer value){
//        Map<String,Integer> product = cart.getProductInCart();
        cart.getProductInCart().put(key,value);
    }
    @Override
    public List<GioHangDtoReponse> laySpTrongGio(){
        return cart.getProductInCart().entrySet().stream().map(m -> new GioHangDtoReponse(repository.findById(m.getKey()).get(),m.getValue()))
                .collect(Collectors.toList());
    }

}
