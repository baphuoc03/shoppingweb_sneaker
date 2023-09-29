package com.example.shoppingweb.service;


import com.example.shoppingweb.model.ThongBaoNhanModel;

import java.util.List;

public interface IThongBaoNhanService {
    List<ThongBaoNhanModel> saveAll(List<ThongBaoNhanModel> thongBaoNhanModel);

    ThongBaoNhanModel save(ThongBaoNhanModel thongBaoNhanModel);

    List<ThongBaoNhanModel> getAllByNguoiNhanId(String idNguoiNhan);
}
