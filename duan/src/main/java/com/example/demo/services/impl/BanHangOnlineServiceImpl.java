package com.example.demo.services.impl;

import com.example.demo.DTO.Top10SanPham;
import com.example.demo.models.*;
import com.example.demo.repositories.BanHangOnLinerepository;
import com.example.demo.services.BanHangOnlineService;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.GioHangChiTietService;
import com.example.demo.viewmodels.TongtienvsTongspchon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Service
public class BanHangOnlineServiceImpl implements BanHangOnlineService {
    @Autowired
    BanHangOnLinerepository banHangOnLinerepository;
    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    GioHangChiTietService gioHangChiTietService;
    @Override
    public Integer tonggiamgia(String idctsp) {
        if(banHangOnLinerepository.tonggiamgia(UUID.fromString(idctsp))==null) {
            return 0;
        }

        return banHangOnLinerepository.tonggiamgia(UUID.fromString(idctsp));
    }

    @Override
    public List<ChiTietSanPham> ctspbanhang() {
        return banHangOnLinerepository.ctspbanhang();
    }

    @Override
    public List<Top10SanPham> top10SanPhamBanChay() {
        return banHangOnLinerepository.top10SanPhamBanChay();
    }

    @Override
    public Integer soluongcon(String idctsp) {
        return banHangOnLinerepository.soluongcon(UUID.fromString(idctsp));
    }

    @Override
    public Integer soluongdaban(String idctsp) {
        return banHangOnLinerepository.soluongdaban(UUID.fromString(idctsp));
    }

    @Override
    public List<ChiTietSanPham> locbanhang(String idHang,
                                           String moTaCam,
                                           String moTaMan,
                                           String moTaMau,
                                           String idRam,
                                           String idRom,
                                           String idPin,
                                           String idDLPin,
                                           String idChip,
                                           String tenSP
    )
    {        return banHangOnLinerepository.locbanhang(idHang,
            moTaCam,
            moTaMan,
            moTaMau,
            idRam,
            idRom,
            idPin,
            idDLPin,
            idChip,
            tenSP);
    }

    @Override
    public List<ChiTietSanPham> ListctspTheoidsp(String idsp) {
        return banHangOnLinerepository.ListctspTheoidsp(UUID.fromString(idsp));
    }

    @Override
    public List<GioHang> ListghTheoidkh(String idkh) {
        return banHangOnLinerepository.ListghTheoidkh(UUID.fromString(idkh));
    }

    @Override
    public Integer sl1ctsptronggh(UUID idgh, UUID idctsp) {
        return banHangOnLinerepository.sl1ctsptronggh(idgh,idctsp);
    }

    @Override
    public List<GioHangChiTiet> ListghctTheoidgh(UUID idgh) {
        return banHangOnLinerepository.ListghctTheoidgh(idgh);
    }

    @Override
    public List<GioHangChiTiet> ListghctTheoIdghvsIdctsp(UUID idgh, UUID idctsp) {
        return banHangOnLinerepository.ListghctTheoIdghvsIdctsp(idgh,idctsp);
    }

    @Override
    public TongtienvsTongspchon TongtienvsTongspchon(UUID idgh) {
        return banHangOnLinerepository.TongtienvsTongspchon(idgh);
    }

    @Override
    public void trangthaighct(Integer trangthai, UUID idgh) {
        banHangOnLinerepository.trangthaighct(trangthai,idgh);
    }

    @Override
    public List<GioHangChiTiet> ListghTheoidghvsTT1(UUID idgh) {
        return banHangOnLinerepository.ListghTheoidghvsTT1(idgh);
    }

    @Override
    public List<DiaChi> Listdiachimotkhachang(UUID idkh) {
        return banHangOnLinerepository.Listdiachimotkhachang(idkh);
    }

    @Override
    public HoaDon timhdtheomahd(String mahd) {
        return banHangOnLinerepository.timhdtheomahd(mahd);
    }

    @Override
    public List<IMEI> timimeitheoidctspVSttO(UUID idctsp) {
        return banHangOnLinerepository.timimeitheoidctspVSttO(idctsp);
    }

    @Override
    public void xoaghcttheoIDGHvsTTO(UUID idgh) {
        banHangOnLinerepository.xoaghcttheoIDGHvsTTO(idgh);
    }

    @Override
    public List<HoaDon> timhoadontheoidkh(UUID idkh) {
        return banHangOnLinerepository.timhoadontheoidkh(idkh);
    }

    @Override
    public List<HoaDonChiTiet> timhoadonchitiettheoidhd(UUID idhd) {
        return banHangOnLinerepository.timhoadonchitiettheoidhd(idhd);
    }

    @Override
    public List<HoaDonChiTiet> listIMEItheoIDHDvsIDCTSP(UUID idhd, UUID idctsp) {
        return banHangOnLinerepository.listIMEItheoIDHDvsIDCTSP(idhd,idctsp);
    }

    @Override
    public void huyhoadon(UUID idhd) {
        banHangOnLinerepository.huyhoadon(idhd);
        banHangOnLinerepository.huyhoadonchitiet(idhd);
        banHangOnLinerepository.updateimeiTTveOtheoidhd(idhd);
    }

    @Override
    public void updateimeiTTveOtheoIDHDvsIDCTSP(UUID idhd, UUID idctsp) {
        banHangOnLinerepository.updateimeiTTveOtheoIDHDvsIDCTSP(idhd,idctsp);
    }

    @Override
    public void XoahdcttheoIDHDvsIDCTSP(UUID idhd, UUID idctsp) {
        banHangOnLinerepository.XoahdcttheoIDHDvsIDCTSP(idhd,idctsp);
    }

    @Override
    public List<ChiTietSanPham> locbanhangcoGIATIEN(String idHang,
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
                                                    BigDecimal tienMax) {
        return banHangOnLinerepository.locbanhangcoGIATIEN(idHang,
                moTaCam,
                moTaMan,
                moTaMau,
                idRam,
                idRom,
                idPin,
                idDLPin,
                idChip,
                tenSP,
                tienMin,
                tienMax);
    }

//    @Override
//    public Boolean ThemSPvaoGHCT(String idkh, String idctsp) {
//System.out.println("-------"+idkh+"------"+idctsp);
////        if (ListghctTheoIdghvsIdctsp(ListghTheoidkh(String.valueOf(idkh)).get(0).getId(), idctsp).size() <= 0) {
////            GioHangChiTiet ghct = new GioHangChiTiet();
////            ghct.setGioHang(ListghTheoidkh(String.valueOf(idkh)).get(0));
////            ghct.setChiTietSanPham(chiTietSanPhamService.findById(idctsp));
////            ghct.setSoLuong(1);
////            ghct.setDonGia(chiTietSanPhamService.findById(idctsp).getGiaBan());
////            BigDecimal giaban = chiTietSanPhamService.findById(idctsp).getGiaBan();
////            Integer giaban1 = Integer.valueOf(String.valueOf(giaban));
////            Integer phantramgiam = tonggiamgia(String.valueOf(idctsp));
////            Integer dgkg = giaban1 - giaban1 / 100 * phantramgiam;
////            BigDecimal dgkg1 = BigDecimal.valueOf(Long.valueOf(String.valueOf(dgkg)));
////            ghct.setDonGiaKhiGiam(dgkg1);
////            gioHangChiTietService.add(ghct);
////
////        }
//        return true;
//    }
}
