package com.example.demo.repositories;

import com.example.demo.models.ManHinh;
import com.example.demo.models.ManHinh;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ManHinhRepository extends JpaRepository<ManHinh, UUID> {


    @Query("select c from ManHinh c  where c.tinhTrang=0")
    Page<ManHinh> getAll(Pageable pageable);

    @Query("select c from ManHinh c  where c.tinhTrang=0")
    List<ManHinh> findAll0();

    @Query("select c from ManHinh c  where c.tinhTrang=1")
    Page<ManHinh> getAll1(Pageable pageable);

    @Query("select c from ManHinh c where c.tinhTrang = 0 and (c.ma like %:search% or c.thongSo " +
            "like %:search% or c.congNghe like %:search% or c.loaiCamUng like %:search% or c.tiLeKhungHinh like %:search% or c.doPhanGiai like %:search%)")
    List<ManHinh> sreach0(String search);

    @Query("select c from ManHinh c where c.tinhTrang = 0 and (c.ma like %:search% or c.thongSo " +
            "like %:search% or c.congNghe like %:search% or c.loaiCamUng like %:search% or c.tiLeKhungHinh like %:search% or c.doPhanGiai like %:search%)")
    List<ManHinh> sreach1(String search);

    @Transactional
    @Modifying
    @Query(value = "update man_hinh set tinh_trang=0, ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void updateTT();
}
