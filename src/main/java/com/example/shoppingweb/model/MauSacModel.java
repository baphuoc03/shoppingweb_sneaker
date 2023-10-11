package com.example.shoppingweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@ToString
@Table(name = "mausac")
public class MauSacModel {
    @Id
    @Column(name = "ma")
    @UuidGenerator
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngaytao")
    @CreationTimestamp
    private Date ngayTao;

    @Column(name = "ngaycapnhat")
    @UpdateTimestamp
    private Date ngayCapNhat;

    public MauSacModel(String ma) {
        this.ma = ma;
    }
}
