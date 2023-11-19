package com.example.demo.services;

import com.example.demo.DTO.Top10SanPham;
import com.example.demo.models.*;
import com.example.demo.repositories.BanHangOnLinerepository;
import com.example.demo.viewmodels.TongtienvsTongspchon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BanHangOnlineService {
    Integer tonggiamgia( String idctsp);
    List<ChiTietSanPham> ctspbanhang();
    List<Top10SanPham> top10SanPhamBanChay();

    Integer soluongcon( String idctsp);
    Integer soluongdaban( String idctsp);
    List<ChiTietSanPham> locbanhang(String idHang,
                                    String moTaCam,
                                    String moTaMan,
                                    String moTaMau,
                                    String idRam,
                                    String idRom,
                                    String idPin,
                                    String idDLPin,
                                    String idChip,
                                    String tenSP);
    List<ChiTietSanPham> ListctspTheoidsp(String idsp);

    List<GioHang> ListghTheoidkh( String idkh);
    Integer sl1ctsptronggh(UUID idgh,UUID idctsp);
    List<GioHangChiTiet> ListghctTheoidgh(UUID idgh);
    List<GioHangChiTiet> ListghctTheoIdghvsIdctsp(UUID idgh,UUID idctsp);
    TongtienvsTongspchon TongtienvsTongspchon( UUID idgh);
    void trangthaighct( Integer trangthai, UUID idgh);
    List<GioHangChiTiet> ListghTheoidghvsTT1( UUID idgh);
    List<DiaChi> Listdiachimotkhachang( UUID idkh);
    HoaDon timhdtheomahd( String mahd);
    List<IMEI> timimeitheoidctspVSttO( UUID idctsp);
    void xoaghcttheoIDGHvsTTO( UUID idgh);
    List<HoaDon> timhoadontheoidkh( UUID idkh);
    List<HoaDonChiTiet> timhoadonchitiettheoidhd( UUID idhd);
    List<HoaDonChiTiet> listIMEItheoIDHDvsIDCTSP(UUID idhd, UUID idctsp);
    void huyhoadon( UUID idhd);
    void updateimeiTTveOtheoIDHDvsIDCTSP( UUID idhd, UUID idctsp);
    void XoahdcttheoIDHDvsIDCTSP(UUID idhd, UUID idctsp);
//    Boolean ThemSPvaoGHCT(String idkh, String idctsp);

    List<ChiTietSanPham> locbanhangcoGIATIEN(String idHang,
                                             String moTaCam,
                                             String moTaMan,
                                             String moTaMau,
                                             String idRam,
                                             String idRom,
                                             String idPin,
                                             String idDLPin,
                                             String idChip,
                                             String tenSP,
                                             BigDecimal tienMin,
                                             BigDecimal tienMax);
}
