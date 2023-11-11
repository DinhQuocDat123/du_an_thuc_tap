package com.example.demo.controllers;

import com.example.demo.config.UserInfoUserDetails;
import com.example.demo.models.*;
import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.services.*;
import com.example.demo.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.security.SecureRandom;

@Component
@Controller
public class BanHangOnlineController {
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private DiaChiService diaChiService;
    @Autowired
    private IMEIService imeiService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private ChipService chipService;
    @Autowired
    private RamService ramService;
    @Autowired
    private RomService romService;
    @Autowired
    private PinService pinService;
    @Autowired
    private HangSanPhamService hangSanPhamService;
    @Autowired
    private HangKhachHangService hangKhachHangService;
    @Autowired
    private DungLuongPinService dungLuongPinService;
    @Autowired
    private ManHinhService manHinhService;
    @Autowired
    private CameraService cameraService;
    @Autowired
    private BanHangOnlineService banHangOnlineService;
    @Autowired
    private GioHangChiTietService gioHangChiTietService;
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private DataIntermediateService dataService;
    @Autowired
    private KhachHangRepository khachHangRepository;


    // Bắt đầu bán hàng
    public Integer kt(Integer so) {
        if (so == Integer.valueOf(0)) {
            return Integer.valueOf(1);
        }
        return so;
    }

    //    private String checkGMAILorTK="0";
    private String idkhachhang = "1";

    //    @GetMapping("/ban-hang-online/dangxuat")
////    public String lgin11() {
////        idkhachhang="1";
////        System.out.println("--------"+idkhachhang);
////        return "login/loginPage";
////    }
    @GetMapping("/lgin")
    public String lgin() {
        idkhachhang = "1";
        return "login/loginPage";
    }

    @GetMapping("/ban-hang-online/hien-thi")
    public String hienThitrangchu(
            Model model
    ) {
        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }

        double tb = tong / 3;
        lamchon = lamchon / 3;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }
        model.addAttribute("lamchon", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
        idkhachhang = "1";
        model.addAttribute("idkhachhang", idkhachhang);
        return "ban-hang-online/trang-chu";
    }

    @GetMapping("/ban-hang-online/home")
    public String hienThitrangchudnhome(
            Model model
    ) {
        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }
        double tb = tong / 3;
        lamchon = lamchon / 3;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }
        model.addAttribute("lamchon", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);
        model.addAttribute("banhangonline", banHangOnlineService);


        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());


//        giohang
        model.addAttribute("tttong", 1);
        if (idkhachhang.equals("1")) {
            return "redirect:/";
        } else {
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
            return "ban-hang-online/trang-chu";
        }

    }

    @GetMapping("/ban-hang-online/taikhoan-matkhau")
    public String hienThitrangchudn(@AuthenticationPrincipal Authentication authentication,
                                    Model model
    ) {
        if (SecurityUtil.getId() != null) {
//            checkGMAILorTK="1";

            double tong = 0;
            Integer lamchon = 0;
            for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
                if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                    tong = tong + 1;
                    lamchon = lamchon + 1;
                }
            }
            double tb = tong / 3;
            lamchon = lamchon / 3;
            if (tb % 1 > 0) {
                lamchon = lamchon + 1;
            }
            model.addAttribute("lamchon", lamchon);
            model.addAttribute("giamgia", banHangOnlineService);
            model.addAttribute("banhangonline", banHangOnlineService);

//lấy id khách hàng
//        KhachHang khachHang=  dataService.getSharedData();
////        System.out.println("id:"+khachHang.getId());
//        idkhachhang=String.valueOf(khachHang.getId());
////        idkhachhang="f33013f5-993b-45d3-9806-dd74f2007522";
// kết thúc lấy id khách hàng
            UserInfoUserDetails userDetails = SecurityUtil.getId();
            idkhachhang = String.valueOf(userDetails.getId());

            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
            model.addAttribute("idkhachhang", idkhachhang);
//        giohang
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
            model.addAttribute("tttong", 1);
            return "redirect:/ban-hang-online/home";
        } else {
            return "redirect:/";
        }


    }

    @GetMapping("/ban-hang-online/gmail")
    public String hienThitrangchudnemail(@AuthenticationPrincipal OAuth2User oauth2User,
                                         Principal principal,
                                         Model model
    ) {
        if (oauth2User != null) {
//            checkGMAILorTK="2";
            double tong = 0;
            Integer lamchon = 0;
            for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
                if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                    tong = tong + 1;
                    lamchon = lamchon + 1;
                }
            }
            double tb = tong / 3;
            lamchon = lamchon / 3;
            if (tb % 1 > 0) {
                lamchon = lamchon + 1;
            }
            model.addAttribute("lamchon", lamchon);
            model.addAttribute("giamgia", banHangOnlineService);
            model.addAttribute("banhangonline", banHangOnlineService);

//lay email
            String email = oauth2User.getAttribute("email");
            String name = oauth2User.getAttribute("name");
            String idToken = oauth2User.getAttribute("id_token");
            System.out.println(principal.getName());

            System.out.println(email);
            System.out.println(name);

            // Kiểm tra xem người dùng đã tồn tại trong cơ sở dữ liệu hay chưa


            if (khachHangRepository.getKhachHangByTaiKhoan(email).isEmpty()) {
                // Người dùng chưa tồn tại, tạo người dùng mới
//            GoogleId token= oauth2User.get;
                String mkh = "";
                Integer sl = khachHangService.findAll().size() + 1;
                if (sl < 9) {
                    mkh = "MKH0" + sl;
                } else {
                    mkh = "MKH" + sl;
                }
                List<KhachHang> khachHangList = khachHangRepository.findAll();
                String email1 = oauth2User.getAttribute("email");
                String name1 = oauth2User.getAttribute("name");
                KhachHang newUser = new KhachHang();
                newUser.setMa(mkh);
                newUser.setEmail(email1);
                newUser.setTaiKhoan(email1);
                newUser.setHoTen(name1);
                newUser.setSdt("0123456789");
                newUser.setDiem(1);
                newUser.setGioiTinh(true);
                String randomPassword = LoginController.generateRandomPassword(8);
                newUser.setTaiKhoan(randomPassword);
                newUser.setNgaySinh(Date.valueOf("1999-1-1"));
                newUser.setTinhTrang(0);
                newUser.setNgayTao(Date.valueOf(LocalDate.now()));
                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


                String hashedPassword = BCrypt.hashpw(randomPassword, BCrypt.gensalt());
                newUser.setMatKhau(hashedPassword);

                // Lưu người dùng mới vào cơ sở dữ liệu
                khachHangRepository.save(newUser);
                //      them gh
                khachHangService.findAll();
                String mghkh = "";
                Integer slgh = gioHangService.findAll().size();
                if (slgh < 10) {
                    mghkh = "MGH0" + slgh;
                } else {
                    mghkh = "MGH" + slgh;
                }
                GioHang ghkh = new GioHang();
                ghkh.setMa(mghkh);
                for (KhachHang kh11 : khachHangService.findAll()) {
                    if (kh11.getMa().equals(mkh)) {
                        ghkh.setKhachHang(kh11);
                        idkhachhang = String.valueOf(kh11.getId());
                        break;
                    }
                }
                gioHangService.add(ghkh);
// het them gh

            } else {
                Optional<KhachHang> idkh = khachHangRepository.getKhachHangByTaiKhoan(email);
                idkhachhang = String.valueOf(idkh.get().getId());
            }

//    het lay email

            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
            model.addAttribute("idkhachhang", idkhachhang);
//        giohang
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
            model.addAttribute("tttong", 1);
            return "redirect:/ban-hang-online/home";


        } else {
            return "redirect:/";
        }

    }


    @GetMapping("/ban-hang-online/dien-thoai-thong-minh")
    public String hienThitrangchudienthoai(
            Model model
    ) {
//
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
//
        model.addAttribute("hangds", hangSanPhamService.findAll0());
        model.addAttribute("camds", cameraService.findAll0());
        model.addAttribute("mands", manHinhService.findAll0());
        model.addAttribute("mauds", mauSacService.findAll0());
        model.addAttribute("ramds", ramService.findAll0());
        model.addAttribute("romds", romService.findAll0());
        model.addAttribute("pinds", pinService.findAll0());
        model.addAttribute("dungds", dungLuongPinService.findAll0());
        model.addAttribute("chipds", chipService.findAll0());
        model.addAttribute("sands", sanPhamService.findAll0());
        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }
        double tb = tong / 8;
        lamchon = lamchon / 8;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }
        model.addAttribute("lamchon1", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
        //gio hang
        if (idkhachhang.equals("1")) {

        } else {
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        }
        model.addAttribute("tttong", 1);
//        //gia max
        Integer max = 0;
        for (ChiTietSanPham ct : chiTietSanPhamService.findAll()) {
            if (Integer.valueOf(String.valueOf(ct.getGiaBan())) > max) {
                max = Integer.valueOf(String.valueOf(ct.getGiaBan()));
            }
        }
//System.out.println("taco---"+max);
        model.addAttribute("max", String.valueOf(max));


        return "ban-hang-online/trang-loc-sanpham";


    }

    @GetMapping("/ban-hang-online/loc-ban-hang/{x1}/{x2}/{x3}/{x4}/{x5}/{x6}/{x7}/{x8}/{x9}/{x10}/{x11}/{x12}")
    public String locbanhang(
            Model model,
            @PathVariable("x1") String x1,
            @PathVariable("x2") String x2,
            @PathVariable("x3") String x3,
            @PathVariable("x4") String x4,
            @PathVariable("x5") String x5,
            @PathVariable("x6") String x6,
            @PathVariable("x7") String x7,
            @PathVariable("x8") String x8,
            @PathVariable("x9") String x9,
            @PathVariable("x10") String x10,
            @PathVariable("x11") String x11,
            @PathVariable("x12") String x12
    ) {


        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.locbanhangcoGIATIEN(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, BigDecimal.valueOf(Double.valueOf(x11)), BigDecimal.valueOf(Double.valueOf(x12)))) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }

        double tb = tong / 8;
        lamchon = lamchon / 8;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }
        model.addAttribute("lamchon1", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("listsp", banHangOnlineService.locbanhangcoGIATIEN(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, BigDecimal.valueOf(Double.valueOf(x11)), BigDecimal.valueOf(Double.valueOf(x12))));

        return "ban-hang-online/single_pase_giao-dien-loc";
    }

    @GetMapping("/ban-hang-online/chi-tiet-san-pham/{idctsp}")
    public String giohang(
            Model model,
            @PathVariable("idctsp") UUID idctsp

    ) {
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
//        chi tiêt san pham
        model.addAttribute("ktcokhong", 1);
        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("motctsp", chiTietSanPhamService.findById(idctsp));
        System.out.println(chiTietSanPhamService.findById(idctsp).getSoLuong());
        model.addAttribute("idctsp", idctsp);
//gio hang
        if (idkhachhang.equals("1")) {

        } else {
            model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        }
        model.addAttribute("tttong", 1);

//danh sách sản phẩm dưới

        double tong = 0;
        Integer lamchon = 0;
        for (ChiTietSanPham ct : banHangOnlineService.ctspbanhang()) {
            if (banHangOnlineService.soluongcon(String.valueOf(ct.getId())) > 0) {
                tong = tong + 1;
                lamchon = lamchon + 1;
            }
        }
        double tb = tong / 8;
        lamchon = lamchon / 8;
        if (tb % 1 > 0) {
            lamchon = lamchon + 1;
        }
        model.addAttribute("lamchon1", lamchon);
        model.addAttribute("giamgia", banHangOnlineService);

        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());

        return "ban-hang-online/trang-chi-tiet-san-pham";


    }


    @GetMapping("/ban-hang-online/loc-chi-tiet-san-pham/{x1}/{x2}/{x3}/{x4}/{x5}")
    public String locbanhangctsp(
            Model model,
            @PathVariable("x1") String x1,
            @PathVariable("x2") String x2,
            @PathVariable("x3") String x3,
            @PathVariable("x4") String x4,
            @PathVariable("x5") String x5

    ) {


        List<ChiTietSanPham> listctsp = banHangOnlineService.locbanhang("null", "null", "null", x1, x3, x2, "null", "null", x4, x5);

        ChiTietSanPham motctsp = new ChiTietSanPham();
        int kt = 0;
        for (ChiTietSanPham ht : listctsp) {
            if (banHangOnlineService.soluongcon(String.valueOf(ht.getId())) > 0) {
                kt = 1;
                motctsp = ht;
                break;
            }
        }
        System.out.println("taco-" + listctsp.size());
        model.addAttribute("tensp", x5);
        model.addAttribute("ktcokhong", kt);
        model.addAttribute("motctsp", motctsp);
        model.addAttribute("listsp", banHangOnlineService.ctspbanhang());
        model.addAttribute("banhangonline", banHangOnlineService);

        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_page_chi-tiet-san-pham";
    }

    @PostMapping("/ban-hang-online/trang-chi-tiet-san-pham/thanh-toan")
    public String nutthanhtoanctsp(
            Model model,
            @RequestParam("idkh") String idkh,
            @RequestParam("idctsp") UUID idctsp,
            @RequestParam("solg") Integer soluong
    ) {

        if (banHangOnlineService.ListghctTheoIdghvsIdctsp(banHangOnlineService.ListghTheoidkh(idkh).get(0).getId(), idctsp).size() > 0) {
            GioHangChiTiet ghctud = banHangOnlineService.ListghctTheoIdghvsIdctsp(banHangOnlineService.ListghTheoidkh(idkh).get(0).getId(), idctsp).get(0);
            Integer slud = ghctud.getSoLuong() + soluong;
            ghctud.setSoLuong(slud);
            gioHangChiTietService.add(ghctud);
        } else {
            GioHangChiTiet ghct = new GioHangChiTiet();
            ghct.setGioHang(banHangOnlineService.ListghTheoidkh(idkh).get(0));
            ghct.setChiTietSanPham(chiTietSanPhamService.findById(idctsp));
            ghct.setSoLuong(soluong);
            ghct.setDonGia(chiTietSanPhamService.findById(idctsp).getGiaBan());
            BigDecimal giaban = chiTietSanPhamService.findById(idctsp).getGiaBan();
            Integer giaban1 = Integer.valueOf(String.valueOf(giaban));
            Integer phantramgiam = banHangOnlineService.tonggiamgia(String.valueOf(idctsp));
            Integer dgkg = giaban1 - giaban1 / 100 * phantramgiam;
            BigDecimal dgkg1 = BigDecimal.valueOf(Long.valueOf(String.valueOf(dgkg)));
            ghct.setDonGiaKhiGiam(dgkg1);
            gioHangChiTietService.add(ghct);
        }
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkh).get(0).getId()));
        model.addAttribute("tttong", 1);
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/trang-gio-hang-chi-tiet";
    }


    @GetMapping("/ban-hang-online/trang-gio-hang-chi-tiet/so-luong/{idghct}/{solg}")
    public String updateslghct(
            Model model,
            @PathVariable("idghct") UUID idghct,
            @PathVariable("solg") Integer soluong
    ) {


        GioHangChiTiet ghct = gioHangChiTietService.findById(idghct);
        ghct.setSoLuong(soluong);
        gioHangChiTietService.add(ghct);
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(ghct.getGioHang().getKhachHang().getId())).get(0).getId()));
        model.addAttribute("banhangonline", banHangOnlineService);
        model.addAttribute("tttong", 1);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_page_gio_hang_chi_tiet";
    }

    @GetMapping("/ban-hang-online/trang-gio-hang-chi-tiet/chon-san-pham/{idghct}/{trangthai}/{idgh}")
    public String chonspghct(
            Model model,

            @PathVariable("idghct") String idghct,
            @PathVariable("trangthai") String trangthai,
            @PathVariable("idgh") UUID idgh
    ) {
        if (idghct.equals("full")) {
            if (trangthai.equals("0")) {
                banHangOnlineService.trangthaighct(0, idgh);
                model.addAttribute("tttong", 0);
            } else {
                banHangOnlineService.trangthaighct(1, idgh);
                model.addAttribute("tttong", 1);
            }
        } else {
            GioHangChiTiet ghct = gioHangChiTietService.findById(UUID.fromString(idghct));
            if (trangthai.equals("0")) {
                ghct.setTinhTrang(0);
                gioHangChiTietService.add(ghct);
            } else {
                ghct.setTinhTrang(1);
                gioHangChiTietService.add(ghct);
            }
        }

        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_page_gio_hang_chi_tiet";
    }

    @GetMapping("/ban-hang-online/trang-gio-hang-chi-tiet/xoa-mot-ghct/{idghct}/{idgh}")
    public String xoamotghct(
            Model model,
            @PathVariable("idghct") UUID idghct,
            @PathVariable("idgh") UUID idgh
    ) {
        gioHangChiTietService.delete(idghct);
        model.addAttribute("tttong", 1);
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_page_gio_hang_chi_tiet";
    }

    @PostMapping("/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-mua-hang")
    public String nutthanhtoantrangghct(
            Model model,
            @RequestParam("idgh") UUID idgh
    ) {
        model.addAttribute("listghctTT", banHangOnlineService.ListghctTheoidgh(idgh));
        model.addAttribute("listghct", banHangOnlineService.ListghTheoidghvsTT1(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/trang-san-pham-duoc-chon-thanh-toan";
    }

    @PostMapping("/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-them-dia-chi")
    public String nutthemdiachi(
            Model model,
            @RequestParam("diachi") String diachi1, @RequestParam("quan") String quan,
            @RequestParam("huyen") String huyen, @RequestParam("thanhpho") String thanhpho,
            @RequestParam("idgh") UUID idgh
    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        DiaChi diaChi = new DiaChi();
        Integer sl = diaChiService.findAll().size();
        String mhd = "";
        if (sl < 10) {
            mhd = "MDC0" + sl;
        } else {
            mhd = "MDC" + sl;
        }
        diaChi.setMa(mhd);
        diaChi.setNgayTao(date);
        diaChi.setNgayCapNhat(date);
        diaChi.setTinhTrang(0);
        diaChi.setDiaChi(diachi1);
        diaChi.setQuan(quan);
        diaChi.setHuyen(huyen);
        diaChi.setThanhPho(thanhpho);
        KhachHang kh = khachHangService.findById(gioHangService.findById(idgh).getKhachHang().getId());
        diaChi.setKhachHang(kh);
        diaChiService.add(diaChi);

        model.addAttribute("listghct", banHangOnlineService.ListghTheoidghvsTT1(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        model.addAttribute("listghctTT", banHangOnlineService.ListghctTheoidgh(idgh));

        return "ban-hang-online/trang-san-pham-duoc-chon-thanh-toan";

    }


//    @PostMapping("/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-dat-hang/{idgh}/{tongtien}/{iddc}/{sdt}")
    @PostMapping("/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-dat-hang")
    public String nutdathang(
            Model model,
            @RequestParam("idgh1") UUID idgh,
            @RequestParam("tongtien1") BigDecimal tongtien,
            @RequestParam("iddc1") UUID iddc,
            @RequestParam("sdt1") String sdt
    ) {
//        them hd
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        HoaDon hd = new HoaDon();
        Integer sl = hoaDonService.findAll().size();
        String mhd = "";
        if (sl < 10) {
            mhd = "MHD0" + sl;
        } else {
            mhd = "MHD" + sl;
        }
        hd.setMa(mhd);
//        hd.setSdt(gioHangService.findById(idgh).getKhachHang().getSdt());
        hd.setSdt(sdt);
        hd.setTongTien(tongtien);
        hd.setNgayTao(date);
        hd.setNgayCapNhat(date);
        hd.setTinhTrang(0);
        hd.setLoai(1);
        hd.setHinhThucThanhToan(2);
        hd.setTinhTrangGiaoHang(0);
        KhachHang kh = khachHangService.findById(gioHangService.findById(idgh).getKhachHang().getId());
        hd.setKhachHang(kh);
        DiaChi dc = diaChiService.findById(iddc);
        hd.setDiaChi(dc);
        hoaDonService.add(hd);
//    them hdct
        List<GioHangChiTiet> listghct = banHangOnlineService.ListghTheoidghvsTT1(idgh);
        for (int a = 0; a < listghct.size(); a = a + 1) {
            for (int b = 0; b < listghct.get(a).getSoLuong(); b = b + 1) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setSoLuong(1);
                hdct.setTinhTrang(0);
                hdct.setDonGia(listghct.get(a).getDonGiaKhiGiam());
                HoaDon hd1 = banHangOnlineService.timhdtheomahd(mhd);
                hdct.setHoaDon(hd1);
                List<IMEI> listimei = banHangOnlineService.timimeitheoidctspVSttO(listghct.get(a).getChiTietSanPham().getId());
                hdct.setImei(listimei.get(0));
                hoaDonChiTietService.add(hdct);
// cập nhật trạng thái imei
                IMEI imei = listimei.get(0);
                imei.setTinhTrang(3);
                imei.setNgayCapNhat(date);
                imeiService.add(imei);
            }
        }
//xoa ghct TT=0 theo idgh
        banHangOnlineService.xoaghcttheoIDGHvsTTO(idgh);
//
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(gioHangService.findById(idgh).getKhachHang().getId())).get(0).getId()));
        model.addAttribute("tttong", 1);
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/dat_hang_thanh_cong";
    }


    @GetMapping("/ban-hang-online/hoa-don-online/{id}")
    public String hoadononline(
            Model model,
            @PathVariable("id") UUID idkh
    ) {
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(idkh)).get(0).getId()));

        model.addAttribute("listhdkh", banHangOnlineService.timhoadontheoidkh(idkh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/trang_hoa_don_khach_hang";
    }


    @GetMapping("/ban-hang-online/xem-hoa-don-chi-tiet/{idhd}")
    public String nutxemchitiethoadon(
            Model model,
            @PathVariable("idhd") UUID idhd
    ) {
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(idkhachhang)).get(0).getId()));

        model.addAttribute("hd", hoaDonService.findById(idhd));
        model.addAttribute("listhdct", banHangOnlineService.timhoadonchitiettheoidhd(idhd));
        model.addAttribute("banhangonline", banHangOnlineService);
        System.out.println("------" + banHangOnlineService.listIMEItheoIDHDvsIDCTSP(UUID.fromString("C0242A2A-F83C-4347-AD29-FEA374AB7CD9"), UUID.fromString("AF372FA0-7E69-4193-BB0E-4DFF72EECD01")).size());
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        model.addAttribute("hienmodal", 0);
        return "ban-hang-online/trang_xem_hoa_don_chi_tiet";
    }

    @GetMapping("/ban-hang-online/xem-hoa-don-chi-tiet/huy-hoa-don/{idhd}")
    public String huyhoadon(
            Model model,
            @PathVariable("idhd") UUID idhd
    ) {
        banHangOnlineService.huyhoadon(idhd);

        model.addAttribute("listhdkh", banHangOnlineService.timhoadontheoidkh(hoaDonService.findById(idhd).getKhachHang().getId()));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }

//        return "ban-hang-online/trang_hoa_don_khach_hang";
        return "ban-hang-online/single_pase_hoa_don_khach_hang";
    }

    @GetMapping("/ban-hang-online/xem-hoa-don-chi-tiet/xoa-chi-tiet-san-pham/{idhdct}")
    public String xoahdct(
            Model model,
            @PathVariable("idhdct") UUID idhdct
    ) {
        System.out.println("idhdct===" + idhdct);
        model.addAttribute("hd", hoaDonChiTietService.findById(idhdct).getHoaDon());
        UUID idhd = hoaDonChiTietService.findById(idhdct).getHoaDon().getId();
        UUID idctsp = hoaDonChiTietService.findById(idhdct).getImei().getChiTietSanPham().getId();
        banHangOnlineService.updateimeiTTveOtheoIDHDvsIDCTSP(idhd, idctsp);
        banHangOnlineService.XoahdcttheoIDHDvsIDCTSP(idhd, idctsp);
        Integer tongtien = 0;
        for (HoaDonChiTiet hdct : banHangOnlineService.timhoadonchitiettheoidhd(idhd)) {
            Integer tien1ctsp = Integer.valueOf(String.valueOf(hdct.getDonGia()));
            tongtien = tongtien + tien1ctsp;
        }
        HoaDon hd = hoaDonService.findById(idhd);
        hd.setTongTien(BigDecimal.valueOf(Long.valueOf(String.valueOf(tongtien))));
        hoaDonService.add(hd);
        model.addAttribute("listhdct", banHangOnlineService.timhoadonchitiettheoidhd(idhd));
        model.addAttribute("banhangonline", banHangOnlineService);
        System.out.println("------" + banHangOnlineService.listIMEItheoIDHDvsIDCTSP(UUID.fromString("C0242A2A-F83C-4347-AD29-FEA374AB7CD9"), UUID.fromString("AF372FA0-7E69-4193-BB0E-4DFF72EECD01")).size());
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        model.addAttribute("hienmodal", 0);
        return "ban-hang-online/single_pase_trang_xem_hoa_don_chi-tiet";
    }


    @GetMapping("/ban-hang-online/xem-hoa-don-chi-tiet/thanh-toan-khi-nhan-hang/{idhd}")
    public String ttknh(
            Model model,
            @PathVariable("idhd") UUID idhd
    ) {
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(idkhachhang)).get(0).getId()));

        HoaDon hd1 = hoaDonService.findById(idhd);
        hd1.setTinhTrang(3);
        hd1.setHinhThucThanhToan(0);
        hoaDonService.add(hd1);
        model.addAttribute("listhdkh", banHangOnlineService.timhoadontheoidkh(hoaDonService.findById(idhd).getKhachHang().getId()));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/trang_hoa_don_khach_hang";

    }


    @PostMapping("/ban-hang-online/hoa-don-chi-tiet/them-dia-chi")
    public String nutthemdiachihdct(
            Model model,
            @RequestParam("diachi") String diachi1, @RequestParam("quan") String quan,
            @RequestParam("huyen") String huyen, @RequestParam("thanhpho") String thanhpho,
            @RequestParam("idhd") UUID idhd
    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        DiaChi diaChi = new DiaChi();
        Integer sl = diaChiService.findAll().size();
        String mhd = "";
        if (sl < 10) {
            mhd = "MDC0" + sl;
        } else {
            mhd = "MDC" + sl;
        }
        diaChi.setMa(mhd);
        diaChi.setNgayTao(date);
        diaChi.setNgayCapNhat(date);
        diaChi.setTinhTrang(0);
        diaChi.setDiaChi(diachi1);
        diaChi.setQuan(quan);
        diaChi.setHuyen(huyen);
        diaChi.setThanhPho(thanhpho);
        KhachHang kh = hoaDonService.findById(idhd).getKhachHang();
        diaChi.setKhachHang(kh);
        diaChiService.add(diaChi);

        model.addAttribute("hd", hoaDonService.findById(idhd));
        model.addAttribute("listhdct", banHangOnlineService.timhoadonchitiettheoidhd(idhd));
        model.addAttribute("banhangonline", banHangOnlineService);
        System.out.println("------" + banHangOnlineService.listIMEItheoIDHDvsIDCTSP(UUID.fromString("C0242A2A-F83C-4347-AD29-FEA374AB7CD9"), UUID.fromString("AF372FA0-7E69-4193-BB0E-4DFF72EECD01")).size());
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        model.addAttribute("hienmodal", 1);
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(idkhachhang)).get(0).getId()));

        return "ban-hang-online/trang_xem_hoa_don_chi_tiet";

    }


    @PostMapping("/ban-hang-online/hoa-don-chi-tiet/cap-nhat-thong-tin-dat-hang")
    public String capnhatthongtindathang(
            Model model,
            @RequestParam("sodienthoai") String sodienthoai,
            @RequestParam("diachi") UUID diachi,
            @RequestParam("idhd") UUID idhd
    ) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        HoaDon hd = hoaDonService.findById(idhd);
        if (hd.getTinhTrangGiaoHang() == 0) {
            hd.setSdt(sodienthoai);
            hd.setDiaChi(diaChiService.findById(diachi));
            hoaDonService.add(hd);
            model.addAttribute("thongbaotinhtranggiaohang", 0);
        } else {
            model.addAttribute("thongbaotinhtranggiaohang", 1);
        }

        model.addAttribute("hd", hoaDonService.findById(idhd));
        model.addAttribute("listhdct", banHangOnlineService.timhoadonchitiettheoidhd(idhd));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        model.addAttribute("hienmodal", 0);
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(idkhachhang)).get(0).getId()));

        return "ban-hang-online/trang_xem_hoa_don_chi_tiet";

    }

    @GetMapping("/ban-hang-online/xem-gio-hang")
    public String nutxemgiohang(
            Model model
    ) {

        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        model.addAttribute("tttong", 1);
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }


        return "ban-hang-online/trang-gio-hang-chi-tiet";
    }


    @GetMapping("/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/{idghct}/{trangthai}/{idgh}")
    public String chonspghctTT(
            Model model,

            @PathVariable("idghct") String idghct,
            @PathVariable("trangthai") String trangthai,
            @PathVariable("idgh") UUID idgh
    ) {
        if (idghct.equals("full")) {
            if (trangthai.equals("0")) {
                banHangOnlineService.trangthaighct(0, idgh);
                model.addAttribute("tttong", 0);
            } else {
                banHangOnlineService.trangthaighct(1, idgh);
                model.addAttribute("tttong", 1);
            }
        } else {
            GioHangChiTiet ghct = gioHangChiTietService.findById(UUID.fromString(idghct));
            if (trangthai.equals("0")) {
                ghct.setTinhTrang(0);
                gioHangChiTietService.add(ghct);
            } else {
                ghct.setTinhTrang(1);
                gioHangChiTietService.add(ghct);
            }
        }

        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_pase_gio_hang_trang_chu";
    }


    @GetMapping("/ban-hang-online/them-san-pham-vao-gio-hang/{idctsp}")
    public String themvaogiohang(
            Model model,
            @PathVariable("idctsp") UUID idctsp
    ) {
        if (banHangOnlineService.ListghctTheoIdghvsIdctsp(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId(), idctsp).size() <= 0) {
            GioHangChiTiet ghct = new GioHangChiTiet();
            ghct.setGioHang(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0));
            ghct.setChiTietSanPham(chiTietSanPhamService.findById(idctsp));
            ghct.setSoLuong(1);
            ghct.setDonGia(chiTietSanPhamService.findById(idctsp).getGiaBan());
            BigDecimal giaban = chiTietSanPhamService.findById(idctsp).getGiaBan();
            Integer giaban1 = Integer.valueOf(String.valueOf(giaban));
            Integer phantramgiam = banHangOnlineService.tonggiamgia(String.valueOf(idctsp));
            Integer dgkg = giaban1 - giaban1 / 100 * phantramgiam;
            BigDecimal dgkg1 = BigDecimal.valueOf(Long.valueOf(String.valueOf(dgkg)));
            ghct.setDonGiaKhiGiam(dgkg1);
            gioHangChiTietService.add(ghct);
        }
        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_pase_gio_hang_trang_chu";

    }


    @GetMapping("/ban-hang-online/single_page_gio_hang_chi_tiet")
    public String nutxemgiohangdongbo(
            Model model
    ) {

        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(idkhachhang).get(0).getId()));
        model.addAttribute("tttong", 1);
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }

        System.out.println("**************************");

        return "ban-hang-online/single_page_gio_hang_chi_tiet";
    }

    @GetMapping("/ban-hang-online/trang-chu/load-gio-hang-trang-chu/{idgh}")
    public String loadghctTT(
            Model model,
            @PathVariable("idgh") UUID idgh
    ) {


        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(idgh));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_pase_gio_hang_trang_chu";
    }

    @GetMapping("/ban-hang-online/trang-chu/load-gio-hang-trang-chu-idghct/{idghct}")
    public String loadghctTTtuidghct(
            Model model,
            @PathVariable("idghct") UUID idghct
    ) {


        model.addAttribute("listghct", banHangOnlineService.ListghctTheoidgh(gioHangChiTietService.findById(idghct).getGioHang().getId()));
        model.addAttribute("banhangonline", banHangOnlineService);
        if (idkhachhang.equals("1")) {
            model.addAttribute("idkhachhang", idkhachhang);
        } else {
            model.addAttribute("khachhangdangnhap", khachHangService.findById(UUID.fromString(idkhachhang)));
            model.addAttribute("idkhachhang", UUID.fromString(idkhachhang));
        }
        return "ban-hang-online/single_pase_gio_hang_trang_chu";
    }


//@Scheduled(cron = "0 35 18 30 9 *")
//public void  inlinhtinh(){
//////         0 giây.
//////         35 phút.
//////         18 giờ.
//////         30 ngày (ngày 5).
//////         9 tháng (tháng 5).
// //          *  ngày cho mặc định
//    System.out.println("Chạy vào ngày 30/9/2023 lúc 18:35:00");
//}

//
//    @Scheduled(fixedRate = 10000)
//    void pk(){
//
//        String abc = "27-10-2023 23:00:00";
//
//// Chuyển đổi chuỗi abc thành đối tượng LocalDateTime
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        LocalDateTime abcDateTime = LocalDateTime.parse(abc, formatter);
//
//// Lấy thời gian hiện tại và định dạng nó
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        String formattedDateTime = currentDateTime.format(formatter);
//        System.out.println("Thời gian hiện tại định dạng: " + formattedDateTime);
//
//
//        //// So sánh xem abcDateTime có lớn hơn currentDateTime 24 giờ không
//        LocalDateTime twentyFourHoursAgo = currentDateTime.minusHours(24);
//        if (abcDateTime.isAfter(twentyFourHoursAgo)) {
//            System.out.println("abc lớn hơn currentDateTime một khoảng thời gian 24 giờ.");
//        } else {
//            System.out.println("abc không lớn hơn currentDateTime một khoảng thời gian 24 giờ.");
//        }
//
//    };


}
