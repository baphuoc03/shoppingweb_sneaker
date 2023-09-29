package com.example.shoppingweb.entitymanager;

import com.example.shoppingweb.dto.filter.SanPhamDtoFilter;
import com.example.shoppingweb.model.SanPhamModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SanPhamEntityManager {
    @Autowired
    private EntityManager entityManager;

    public List<SanPhamModel> filterMultipleProperties(SanPhamDtoFilter sanPham){
        StringBuilder jpql = new StringBuilder("select s FROM SanPhamModel s WHERE s.trangThai = true ");

        if(sanPham.getTen() != null){
            jpql.append(" And (s.ten like '%"+sanPham.getTen()+"%' Or s.ma like '%"+sanPham.getTen()+"%')");
//            queryBuider.append("And s.ten like '%"+sanPham.getTen()+"%'");
        }

        if(sanPham.getMauSac()!=null) jpql.append(" And s.mauSac.ma = '"+sanPham.getMauSac()+"'");
        if(sanPham.getDongSanPham()!=null) jpql.append(" And s.dongSanPham.id = '"+ sanPham.getDongSanPham()+"'");
        if(sanPham.getKieuDang()!=null) jpql.append(" And s.kieuDang.id = '" + sanPham.getKieuDang()+"'");
        if(sanPham.getChatLieu()!=null) jpql.append(" And s.chatLieu.id = '" + sanPham.getChatLieu()+"'");
        if(sanPham.getGiaBan()!=null) jpql.append(" And s.giaBan >= " + sanPham.getGiaBan());
        if(sanPham.getGiaMax()!=null) jpql.append(" And s.giaBan <= " + sanPham.getGiaMax());


        System.out.println(String.valueOf(jpql));
        Query query = entityManager.createQuery(String.valueOf(jpql));


        return query.getResultList();
    }
}
