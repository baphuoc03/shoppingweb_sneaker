package com.example.shoppingweb.repository;

import com.example.shoppingweb.model.MauSacModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMauSacRepository extends JpaRepository<MauSacModel,String> {
}
