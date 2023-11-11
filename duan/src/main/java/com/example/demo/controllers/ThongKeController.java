package com.example.demo.controllers;

import com.example.demo.DTO.DoanhThuHang;
import com.example.demo.DTO.DoanhThuKhachHang;
import com.example.demo.DTO.DoanhThuNhanVien;
import com.example.demo.DTO.DoanhThuSanPham;
import com.example.demo.DTO.DoanhThuTheoThang;
import com.example.demo.services.ThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.sql.Date;
import java.util.List;

@RequestMapping("/thong-ke")
@Controller
public class ThongKeController {

    @Autowired
    private ThongKeService thongKeService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {

        List<DoanhThuTheoThang> doanhThuTheoThangs = thongKeService.doanhThu();
        List<DoanhThuTheoThang> selected = thongKeService.selectedYear();

        model.addAttribute("listDoanhThu", doanhThuTheoThangs);
        model.addAttribute("listYear", selected);

        DecimalFormat df = new DecimalFormat("#,###");
//        model.addAttribute("listCount", thongKeService.countHD());
//        model.addAttribute("listAvg",df.format(thongKeService.avgHD()));
        model.addAttribute("contentPage", "../thongke/thong-ke.jsp");
        return "home/layout";
    }


    @PostMapping("/loc-nam")
    public String locNam(Model model, @ModelAttribute("namSelect") Integer nam) {
        List<DoanhThuTheoThang> selected = thongKeService.selectedYear();
        model.addAttribute("listYear", selected);

        List<DoanhThuTheoThang> doanhThuTheoThangs = thongKeService.loctheonam(nam);
        model.addAttribute("listDoanhThu", doanhThuTheoThangs);
        model.addAttribute("contentPage", "../thongke/thong-ke.jsp");
        return "home/layout";
    }

    @PostMapping("/loc-hang")
    public String locHang(Model model, @ModelAttribute("hangSelect") String hang) {
        List<DoanhThuSanPham> doanhThuTheoHang = thongKeService.locHang(hang);
        model.addAttribute("listDoanhThuSP", doanhThuTheoHang);

        List<DoanhThuSanPham> selected = thongKeService.selectedHang();
        model.addAttribute("listHang", selected);
        model.addAttribute("contentPage", "../thongke/thong-ke-sp.jsp");
        return "home/layout";
    }


    @GetMapping("/hien-thi-sp")
    public String hienThiDoanhThuSP(Model model) {
        List<DoanhThuSanPham> selected = thongKeService.selectedHang();
        model.addAttribute("listHang", selected);
        model.addAttribute("contentPage", "../thongke/thong-ke-sp.jsp");
        return "home/layout";
    }


    @GetMapping("/hien-thi-hang")
    public String hienThiHang(Model model) {
        model.addAttribute("contentPage", "../thongke/thong-ke-hang.jsp");
        return "home/layout";
    }

    @PostMapping("/loc-thoi-gian")
    public String locTime(Model model, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        List<DoanhThuHang> doanhThuTheoHang = thongKeService.doanhThuHang();
        model.addAttribute("listDoanhThuHang", doanhThuTheoHang);

        List<DoanhThuHang> locHang = thongKeService.locdoanhThuHang(startDate,endDate);
        model.addAttribute("listDoanhThuHang", locHang);
        model.addAttribute("contentPage", "../thongke/thong-ke-hang.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-nhan-vien")
    public String doanhThuNhanVien(Model model) {
        model.addAttribute("contentPage", "../thongke/thong-ke-nhan-vien.jsp");
        return "home/layout";
    }

    @PostMapping("/loc-thoi-gian-nv")
    public String locTimeNhanVien(Model model, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        List<DoanhThuNhanVien> locHang = thongKeService.locDoanhThuNhanVien(startDate,endDate);
        model.addAttribute("listDoanhThuNhanVien", locHang);
        model.addAttribute("contentPage", "../thongke/thong-ke-nhan-vien.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-khach-hang")
    public String doanhThuKhachHang(Model model) {
//        List<DoanhThuKhachHang> doanhThuKhachHangGioiTinhs = thongKeService.doanhThuKhachHangGioiTinh();
//        model.addAttribute("listDoanhThuKhachHangGioiTinh", doanhThuKhachHangGioiTinhs);
//
//        List<DoanhThuKhachHang> doanhThuKhachHangs = thongKeService.doanhThuKhachHang();
//        model.addAttribute("listDoanhThuKhachHang", doanhThuKhachHangs);
        model.addAttribute("contentPage", "../thongke/thong-ke-khach-hang.jsp");
        return "home/layout";
    }

    @PostMapping("/loc-thoi-gian-kh")
    public String locKhachHang(Model model, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        List<DoanhThuKhachHang> locDoanhThuKhachHang = thongKeService.locDoanhThuKhachHang(startDate,endDate);
        List<DoanhThuKhachHang> locDoanhThuKhachHangGioiTinh = thongKeService.locDoanhThuKhachHangGioiTinh(startDate,endDate);
        model.addAttribute("listDoanhThuKhachHang", locDoanhThuKhachHang);
        model.addAttribute("listDoanhThuKhachHangGioiTinh", locDoanhThuKhachHangGioiTinh);
        model.addAttribute("contentPage", "../thongke/thong-ke-khach-hang.jsp");
        return "home/layout";
    }
}
