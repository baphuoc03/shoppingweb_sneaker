package com.example.shoppingweb.service.seucrity;

import com.example.shoppingweb.config.security.Customer;
import com.example.shoppingweb.model.KhachHangModel;
import com.example.shoppingweb.repository.IKhachHangRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements UserDetailsService {

    private IKhachHangRepository khachHangRepository;

    public CustomerService(IKhachHangRepository khachHangRepository) {
        this.khachHangRepository = khachHangRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KhachHangModel khachHangModel = khachHangRepository.findById(username).orElse(null);
        if(khachHangModel==null){
            throw new UsernameNotFoundException("Username không tồn tại");
        }
        return new Customer(khachHangModel);
    }
}
