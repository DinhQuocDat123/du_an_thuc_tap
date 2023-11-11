package com.example.demo.repositories;

import com.example.demo.models.*;
import com.example.demo.viewmodels.KhachHangLSMuaHang;
import com.example.demo.viewmodels.TongtienvsTongspchon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface BanHangOnLinerepository extends JpaRepository<KhachHang, UUID> {


    @PersistenceContext
    EntityManager entityManager = null;

    @Transactional
    @Query(value = "DECLARE @bienb int; " +
            "SET @bienb = (SELECT SUM(b.so_tien_giam) AS tonggiamgia FROM san_pham_giam_gia a LEFT JOIN khuyen_mai b ON a.id_khuyen_mai = b.id " +
            "             WHERE a.id_chi_tiet_san_pham = :idctsp AND a.tinh_trang = 0 AND b.tinh_trang = 0); " +
            "IF @bienb > 80 " +
            "BEGIN " +
            "   SET @bienb = 80; " +
            "END; " +
            "SELECT CAST(@bienb AS int);", nativeQuery = true)
    Integer tonggiamgia(UUID idctsp);

    @Query("select ct from SanPham sp left join ChiTietSanPham ct on sp.id=ct.sanPham.id where sp.tinhTrang=0 and ct.tinhTrang=0")
    List<ChiTietSanPham> ctspbanhang();



    @Transactional
    @Query(value = "DECLARE @bienb int; " +
            "SET @bienb = (select COUNT(id_chi_tiet_san_pham) from " +
            "imei where tinh_trang=0 and id_chi_tiet_san_pham=:idctsp) ;" +
            "SELECT CAST(@bienb AS int);", nativeQuery = true)
    Integer soluongcon(UUID idctsp);


    @Transactional
    @Query(value = "DECLARE @bienb int; " +
            "SET @bienb = (select COUNT(id_chi_tiet_san_pham) from " +
            "imei where tinh_trang=1 and id_chi_tiet_san_pham=:idctsp) ;" +
            "SELECT CAST(@bienb AS int);", nativeQuery = true)
    Integer soluongdaban(UUID idctsp);

    @Query("SELECT ct FROM ChiTietSanPham ct LEFT JOIN SanPham sp ON ct.sanPham.id = sp.id " +
            "LEFT JOIN HangSanPham hang ON sp.hangSanPham.id = hang.id " +
            "LEFT JOIN Ram ram ON ct.ram.id = ram.id " +
            "LEFT JOIN Rom rom ON ct.rom.id = rom.id " +
            "LEFT JOIN Pin pin ON ct.pin.id = pin.id " +
            "LEFT JOIN MauSac ms ON ct.mauSac.id = ms.id " +
            "LEFT JOIN Chip chip ON ct.chip.id = chip.id " +
            "LEFT JOIN DungLuongPin dungLuong ON pin.dungLuongPin.id = dungLuong.id " +
            "LEFT JOIN ManHinh manHinh ON sp.manHinh.id = manHinh.id " +
            "LEFT JOIN Camera cam ON sp.camera.id = cam.id " +
            "WHERE " +
            "(:idHang = 'null' OR hang.ten = :idHang) " +
            "AND (:idRam = 'null' OR ram.dungLuong = :idRam) " +
            "AND (:idRom = 'null' OR rom.dungLuong = :idRom) " +
            "AND (:idPin = 'null' OR pin.loaiPin = :idPin) " +
            "AND (:idDLPin = 'null' OR dungLuong.thongSo = :idDLPin) " +
            "AND (:idChip = 'null' OR chip.ten = :idChip) " +
            "AND (:moTaMan = 'null' OR manHinh.thongSo = :moTaMan) " +
            "AND (:moTaCam = 'null' OR cam.thongSo = :moTaCam) " +
            "AND (:moTaMau = 'null' OR ms.ten = :moTaMau) " +
            "AND (:tenSP = 'null' OR sp.ten = :tenSP) "+
            "AND (ct.tinhTrang=0) "
    )
    List<ChiTietSanPham> locbanhang(@Param("idHang") String idHang,
                                    @Param("moTaCam") String moTaCam,
                                    @Param("moTaMan") String moTaMan,
                                    @Param("moTaMau") String moTaMau,
                                    @Param("idRam") String idRam,
                                    @Param("idRom") String idRom,
                                    @Param("idPin") String idPin,
                                    @Param("idDLPin") String idDLPin,
                                    @Param("idChip") String idChip,
                                    @Param("tenSP") String tenSP);


    @Query("SELECT ct FROM ChiTietSanPham ct LEFT JOIN SanPham sp ON ct.sanPham.id = sp.id " +
            "LEFT JOIN HangSanPham hang ON sp.hangSanPham.id = hang.id " +
            "LEFT JOIN Ram ram ON ct.ram.id = ram.id " +
            "LEFT JOIN Rom rom ON ct.rom.id = rom.id " +
            "LEFT JOIN Pin pin ON ct.pin.id = pin.id " +
            "LEFT JOIN MauSac ms ON ct.mauSac.id = ms.id " +
            "LEFT JOIN Chip chip ON ct.chip.id = chip.id " +
            "LEFT JOIN DungLuongPin dungLuong ON pin.dungLuongPin.id = dungLuong.id " +
            "LEFT JOIN ManHinh manHinh ON sp.manHinh.id = manHinh.id " +
            "LEFT JOIN Camera cam ON sp.camera.id = cam.id " +
            "WHERE " +
            "(:idHang = 'null' OR hang.ten = :idHang) " +
            "AND (:idRam = 'null' OR ram.dungLuong = :idRam) " +
            "AND (:idRom = 'null' OR rom.dungLuong = :idRom) " +
            "AND (:idPin = 'null' OR pin.loaiPin = :idPin) " +
            "AND (:idDLPin = 'null' OR dungLuong.thongSo = :idDLPin) " +
            "AND (:idChip = 'null' OR chip.ten = :idChip) " +
            "AND (:moTaMan = 'null' OR manHinh.thongSo = :moTaMan) " +
            "AND (:moTaCam = 'null' OR cam.thongSo = :moTaCam) " +
            "AND (:moTaMau = 'null' OR ms.ten = :moTaMau) " +
            "AND (:tenSP = 'null' OR sp.ten = :tenSP) " +
            "AND (ct.giaBan >= :tienMin AND ct.giaBan <= :tienMax)"+
            "AND (ct.tinhTrang=0) "
    )
    List<ChiTietSanPham> locbanhangcoGIATIEN(@Param("idHang") String idHang,
                                             @Param("moTaCam") String moTaCam,
                                             @Param("moTaMan") String moTaMan,
                                             @Param("moTaMau") String moTaMau,
                                             @Param("idRam") String idRam,
                                             @Param("idRom") String idRom,
                                             @Param("idPin") String idPin,
                                             @Param("idDLPin") String idDLPin,
                                             @Param("idChip") String idChip,
                                             @Param("tenSP") String tenSP,
                                             @Param("tienMin") BigDecimal tienMin,
                                             @Param("tienMax") BigDecimal tienMax);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.sanPham.id=:idsp ")
    List<ChiTietSanPham> ListctspTheoidsp(@Param("idsp") UUID idsp);

    @Query("select gh from  GioHang gh where gh.khachHang.id=:idkh ")
    List<GioHang> ListghTheoidkh(@Param("idkh") UUID idkh);


    @Transactional
    @Query(value="DECLARE @bienb int;\n" +
            "set @bienb=(\n" +
            "select sum (so_luong) from gio_hang_chi_tiet \n" +
            "where id_gio_hang=:idgh and id_chi_tiet_san_pham=:idctsp\n" +
            ")\n" +
            "if @bienb is null\n" +
            "begin\n" +
            "set @bienb=0;\n" +
            "end\n" +
            "SELECT CAST(@bienb AS int); ", nativeQuery = true)
    Integer sl1ctsptronggh(@Param("idgh") UUID idgh,@Param("idctsp") UUID idctsp);

    @Query("select ghct from  GioHangChiTiet ghct where ghct.gioHang.id=:idgh ")
    List<GioHangChiTiet> ListghctTheoidgh(@Param("idgh") UUID idgh);

    @Query("select ghct from  GioHangChiTiet ghct where ghct.gioHang.id=:idgh and ghct.chiTietSanPham.id=:idctsp ")
    List<GioHangChiTiet> ListghctTheoIdghvsIdctsp(@Param("idgh") UUID idgh,@Param("idctsp") UUID idctsp);

    @Query(value = "select sum(so_luong*don_gia_khi_giam) as tongtien,\n" +
            "COUNT(id) as tongsanphamchon  from\n" +
            "gio_hang_chi_tiet \n" +
            "where tinh_trang=0 and id_gio_hang=:idgh",nativeQuery = true)
    TongtienvsTongspchon TongtienvsTongspchon(@Param("idgh") UUID idgh);

    @Transactional
    @Modifying
    @Query(value = "update gio_hang_chi_tiet set tinh_trang=:trangthai where id_gio_hang=:idgh", nativeQuery = true)
    void trangthaighct(@Param("trangthai") Integer trangthai,@Param("idgh") UUID idgh);

    @Query("select ghct from  GioHangChiTiet ghct where ghct.gioHang.id=:idgh and  ghct.tinhTrang=0")
    List<GioHangChiTiet> ListghTheoidghvsTT1(@Param("idgh") UUID idgh);

    @Query("select dc from  DiaChi dc where dc.khachHang.id=:idkh and  dc.tinhTrang=0")
    List<DiaChi> Listdiachimotkhachang(@Param("idkh") UUID idkh);

    @Query("select hd from  HoaDon hd where hd.ma=:mahd")
    HoaDon timhdtheomahd(@Param("mahd") String mahd);

    @Query("select im from  IMEI im where im.chiTietSanPham.id=:idctsp and im.tinhTrang=0")
    List<IMEI> timimeitheoidctspVSttO(@Param("idctsp") UUID idctsp);

    @Transactional
    @Modifying
    @Query("delete from GioHangChiTiet ghct where ghct.gioHang.id=:idgh and ghct.tinhTrang=0")
    void xoaghcttheoIDGHvsTTO(@Param("idgh") UUID idgh);

    @Query("select hd from  HoaDon hd where hd.khachHang.id=:idkh ")
    List<HoaDon> timhoadontheoidkh(@Param("idkh") UUID idkh);

    @Query("select hdct from  HoaDonChiTiet hdct where hdct.hoaDon.id=:idhd   ")
    List<HoaDonChiTiet> timhoadonchitiettheoidhd(@Param("idhd") UUID idhd);

    @Query("select hdct from  HoaDonChiTiet hdct where hdct.hoaDon.id=:idhd and hdct.imei.chiTietSanPham.id=:idctsp ")
    List<HoaDonChiTiet> listIMEItheoIDHDvsIDCTSP(@Param("idhd") UUID idhd,@Param("idctsp") UUID idctsp);

    @Transactional
    @Modifying
    @Query(value = "update HoaDon  hd set  hd.tinhTrang=8 where  hd.id=:idhd")
    void huyhoadon(@Param("idhd") UUID idhd);

    @Transactional
    @Modifying
    @Query(value = "update HoaDonChiTiet  hd set  hd.tinhTrang=8 where  hd.hoaDon.id=:idhd")
    void huyhoadonchitiet(@Param("idhd") UUID idhd);

    @Transactional
    @Modifying
    @Query(value = "update IMEI  im set  im.tinhTrang=0 where  im.id in (select hdct.imei.id from  HoaDonChiTiet hdct where hdct.hoaDon.id=:idhd)")
    void updateimeiTTveOtheoidhd(@Param("idhd") UUID idhd);


    @Transactional
    @Modifying
    @Query(value = "update IMEI  im set  im.tinhTrang=0 where  im.id in (select hdct.imei.id from  HoaDonChiTiet hdct where hdct.hoaDon.id=:idhd and hdct.imei.chiTietSanPham.id=:idctsp)")
    void updateimeiTTveOtheoIDHDvsIDCTSP(@Param("idhd") UUID idhd,@Param("idctsp") UUID idctsp);

    @Transactional
    @Modifying
    @Query(value ="delete from  HoaDonChiTiet hdct where hdct.id in (select hdct.id from  HoaDonChiTiet hdct where hdct.hoaDon.id=:idhd and hdct.imei.chiTietSanPham.id=:idctsp)")
    void XoahdcttheoIDHDvsIDCTSP(@Param("idhd") UUID idhd,@Param("idctsp") UUID idctsp);


}
