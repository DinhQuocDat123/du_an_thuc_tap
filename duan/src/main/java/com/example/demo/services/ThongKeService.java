package com.example.demo.services;

import com.example.demo.DTO.DoanhThuHang;
import com.example.demo.DTO.DoanhThuKhachHang;
import com.example.demo.DTO.DoanhThuNhanVien;
import com.example.demo.DTO.DoanhThuSanPham;
import com.example.demo.DTO.DoanhThuTheoThang;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ThongKeService {

    int countHD();
    int avgHD();
    List<DoanhThuTheoThang> doanhThu();
    List<DoanhThuTheoThang> selectedYear();
    List<DoanhThuTheoThang> loctheonam(Integer Nam);

    List<DoanhThuHang> doanhThuHang();
    List<DoanhThuHang> locdoanhThuHang(Date startDate, Date endDate);

    List<DoanhThuSanPham> doanhThuSanPham();
    List<DoanhThuSanPham> selectedHang();
    List<DoanhThuSanPham> locHang(String ten);
    List<DoanhThuNhanVien> doanhThuNhanVien();

    List<DoanhThuNhanVien> locDoanhThuNhanVien(Date startDate, Date endDate);

    List<DoanhThuKhachHang> doanhThuKhachHang();
    List<DoanhThuKhachHang> doanhThuKhachHangGioiTinh();

    List<DoanhThuKhachHang> locDoanhThuKhachHang(Date startDate,Date endDate);
    List<DoanhThuKhachHang> locDoanhThuKhachHangGioiTinh( Date startDate, Date endDate);


}
