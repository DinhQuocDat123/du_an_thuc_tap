package com.example.demo.services;

import com.example.demo.models.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ChiTietSanPhamService {
    public Page<ChiTietSanPham> getAll(Pageable pageable);

    public List<ChiTietSanPham> findAll();

    public List<ChiTietSanPham> findAll0();

    public List<ChiTietSanPham> search(String ten);

    public List<ChiTietSanPham> search1(String ten);

    public List<ChiTietSanPham> loc(UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam);

    public List<ChiTietSanPham> locBanHang(UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam, BigDecimal giaBanMin, BigDecimal giaBanMax);

    public ChiTietSanPham findById(UUID id);

    public ChiTietSanPham getChiTiet(UUID id);

    public ChiTietSanPham getChiTiet2(UUID id);

    public ChiTietSanPham add(ChiTietSanPham chiTietSanPham);

    public ChiTietSanPham update(UUID id, ChiTietSanPham chiTietSanPham);

    public ChiTietSanPham update1(ChiTietSanPham chiTietSanPham);

    public Boolean delete(UUID id);

    List<ChiTietSanPham> getAllTTOn();

    List<ChiTietSanPham> getAllTTOff();

    List<ChiTietSanPham> finAllTTOff();

    List<ChiTietSanPham> findAllHang(UUID id);

    List<ChiTietSanPham> findAllMan(UUID id);

    List<ChiTietSanPham> findAllChip(UUID id);

    List<ChiTietSanPham> findAllRam(UUID id);

    List<ChiTietSanPham> findAllRom(UUID id);

    List<ChiTietSanPham> findAllPin(UUID id);

    List<ChiTietSanPham> findAllCam(UUID id);

}


