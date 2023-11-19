package com.example.demo.repositories;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.IMEI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface IMEIRepository extends JpaRepository<IMEI, UUID> {
    @Query("select i from IMEI i left join ChiTietSanPham ct on i.chiTietSanPham.id=ct.id where ct.id=:id and i.tinhTrang=0")
    List<IMEI> getIMEI(UUID id);

    @Query("select i from IMEI i where i.id=:id")
    List<IMEI> showIMEI(UUID id);

    @Query("select i from IMEI i where i.tinhTrang=1 and (i.soImei like %:imei% or i.ma like %:imei% or i.chiTietSanPham.sanPham.ten like %:imei%)")
    List<IMEI> searchIMEIOff(String imei);

    @Query("select i from IMEI i where i.tinhTrang=2 and (i.soImei like %:imei% or i.ma like %:imei% or i.chiTietSanPham.sanPham.ten like %:imei%)")
    List<IMEI> searchIMEIOff2(String imei);

    @Query("select i from IMEI i where i.tinhTrang=0 and (i.soImei like %:imei% or i.ma like %:imei% or i.chiTietSanPham.sanPham.ten like %:imei%)")
    List<IMEI> searchIMEIOn(String imei);

    @Query("select i from IMEI i where i.tinhTrang=0 and i.chiTietSanPham.id=:idS and (i.soImei like %:imei% or i.ma like %:imei% or i.chiTietSanPham.sanPham.ten like %:imei%)")
    List<IMEI> searchIMEIBanHang(String imei, UUID idS);

    @Query(value = "SELECT id FROM IMEI WHERE so_imei =:imei", nativeQuery = true)
    String searchSoImei2(@Param("imei") String imei);

    @Query(value = "SELECT * FROM IMEI WHERE so_imei =:imei and tinh_trang=0", nativeQuery = true)
    IMEI searchSoImei(@Param("imei") String imei);

    @Transactional
    @Modifying
    @Query("update IMEI i set i.tinhTrang= 1,i.ngayCapNhat=:date where i.id=" +
            "(select hdct.imei.id from HoaDonChiTiet  hdct where hdct.id=:id)")
    void updateImei(Date date, UUID id);

    @Transactional
    @Modifying
    @Query("update IMEI i set i.tinhTrang= 3,i.ngayCapNhat=:date where i.id=:id")
    void updateImeiChoXuLy(Date date, UUID id);

    @Transactional
    @Modifying
    @Query("update IMEI i set i.tinhTrang= 0,i.ngayCapNhat=:date where i.id= " +
            "(select hdct.imei.id from HoaDonChiTiet  hdct where hdct.id=:id)")
    void updateImei1(Date date, UUID id);

    @Query("select imei from  IMEI imei where imei.tinhTrang=1 or imei.tinhTrang=3")
    List<IMEI> getImeiOfff();

    @Query("select imei from  IMEI imei where imei.tinhTrang=1 and imei.tinhTrang=3")
    List<IMEI> getImeiOff();


    @Query("select imei from  IMEI imei where imei.tinhTrang=0 ")
    List<IMEI> getImeiOn();

    @Query("select imei from  IMEI imei where imei.tinhTrang=0 ")
    List<IMEI> findAll0();


    @Query("select imei from  IMEI imei where imei.tinhTrang=2 ")
    List<IMEI> getImeiOff3();

    @Query("select imei from  IMEI imei where imei.tinhTrang=2 ")
    List<IMEI> findAll3();

    @Query("select imei from  IMEI imei where imei.chiTietSanPham.sanPham.id=:id and imei.tinhTrang=0")
    List<IMEI> statusSanPham(UUID id);

    @Query("select imei from  IMEI imei where imei.tinhTrang=0 and imei.chiTietSanPham.id=:id ")
    List<IMEI> statusCTSP(UUID id);
}
