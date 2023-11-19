package com.example.shoppingweb.service.seucrity;
import com.example.shoppingweb.config.security.AdminUser;
import com.example.shoppingweb.model.NhanVienModel;
import com.example.shoppingweb.repository.INhanVienRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAdminService implements UserDetailsService {

    private INhanVienRepository nhanVienRepository;

    public UserAdminService(INhanVienRepository nhanVienRepository) {
        this.nhanVienRepository = nhanVienRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NhanVienModel nhanVienModel = nhanVienRepository.findById(username).orElse(null);
        if(nhanVienModel==null){
            throw new UsernameNotFoundException("Username không tồn tại");
        }
        return new AdminUser(nhanVienModel);
    }
}
