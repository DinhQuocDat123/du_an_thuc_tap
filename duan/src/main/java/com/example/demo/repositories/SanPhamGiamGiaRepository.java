package com.example.demo.repositories;

import com.example.demo.models.IMEI;
import com.example.demo.models.SanPhamGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SanPhamGiamGiaRepository extends JpaRepository<SanPhamGiamGia, UUID> {

    @Query("select i from SanPhamGiamGia i where i.tinhTrang=1 and (i.chiTietSanPham.sanPham.ten like %:spgg% or i.khuyenMai.ten like %:spgg% )")
    List<SanPhamGiamGia> searchSPGGOff(String spgg);
    @Query("select i from SanPhamGiamGia i where i.tinhTrang=0 and (i.chiTietSanPham.sanPham.ten like %:spgg% or i.khuyenMai.ten like %:spgg% )")
    List<SanPhamGiamGia> searchSPGGOn(String spgg);
    @Query("select i from  SanPhamGiamGia i where i.tinhTrang=1 ")
    Page<SanPhamGiamGia> getSPGGOff(Pageable pageable);
    @Query("select i from  SanPhamGiamGia i where i.tinhTrang=0 ")
    Page<SanPhamGiamGia> getSPGGOn(Pageable pageable);
    @Query("select i from  SanPhamGiamGia i where i.tinhTrang=1 ")
    List<SanPhamGiamGia> getSPGGOff();
}
