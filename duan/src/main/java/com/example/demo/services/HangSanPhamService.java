package com.example.demo.services;

import com.example.demo.models.HangSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface HangSanPhamService {
    public Page<HangSanPham> getAll(Pageable pageable);

    public List<HangSanPham> findAll();

    public List<HangSanPham> findAll0();

    public HangSanPham findById(UUID id);

    public HangSanPham add(HangSanPham hangSanPham);

    public HangSanPham update(UUID id, HangSanPham hangSanPham);

    public Boolean delete(UUID id);

    List<HangSanPham> search(String dungluong);

    List<HangSanPham> search2(String dungluong);

    public void updateTT();

    public Page<HangSanPham> getAll0(Pageable pageable);

    Page<HangSanPham> getall1(Pageable pageable);
}


