package com.example.demo.controllers;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.DiaChi;
import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.models.KhachHang;
import com.example.demo.models.NhanVien;
import com.example.demo.models.QuyDoi;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.DataIntermediateService;
import com.example.demo.services.DiaChiService;
import com.example.demo.services.HoaDonChiTietService;
import com.example.demo.services.HoaDonService;
import com.example.demo.services.IMEIService;
import com.example.demo.services.KhachHangService;
import com.example.demo.services.NhanVienService;
import com.example.demo.services.QuyDoiService;
import com.example.demo.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/don-hang")
public class DonHangController {
    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private DiaChiService diaChiService;

    @Autowired
    private QuyDoiService quyDoiService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private IMEIService imeiService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    DataIntermediateService dataIntermediateService;

    private UUID idHoaDon = null;
    BigDecimal total = BigDecimal.ZERO;
    private HoaDon hoaDonnn = new HoaDon();

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("donHang") HoaDon donHang) {

        List<HoaDon> page = hoaDonService.donHang();
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
        model.addAttribute("listHoaDon", page);
        return "home/layout";
    }

    @PostMapping("/loc")
    public String locDonHang(Model model,
                             @RequestParam(value = "nhanVien", required = false) UUID idNV,
                             @RequestParam(value = "khachHang", required = false) UUID idKH,
                             @RequestParam(value = "diaChi", required = false) UUID idDC,
                             @RequestParam(value = "trangThai", required = false) Integer trangThai,
                             @RequestParam(value = "startDate", required = false) String startDate,
                             @RequestParam(value = "receiveStartDate", required = false) String receiveStartDate,
                             @RequestParam(value = "shipStartDate", required = false) String shipStartDate,
                             @RequestParam(value = "endDate", required = false) String endDate,
                             @RequestParam(value = "receiveEndDate", required = false) String receiveEndDate,
                             @RequestParam(value = "shipEndDate", required = false) String shipEndDate,
                             @ModelAttribute("nhanVien") NhanVien nhanVien,
                             @ModelAttribute("khachHang") KhachHang khachHang,
                             @ModelAttribute("diaChi") DiaChi diaChi,
                             @ModelAttribute("hoaDon") HoaDon hoaDon) {

        if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() &&
                receiveEndDate.isEmpty() && shipEndDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    null, null, null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() && receiveEndDate.isEmpty() && shipEndDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    null, null, null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        } else {
            List<HoaDon> listHoaDon = hoaDonService.locDonHang(idKH, idNV, idDC, trangThai,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
            return "home/layout";
        }
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("donHang") HoaDon hoaDon,
                         @RequestParam("search") String search) {
        List<HoaDon> list = hoaDonService.searchDonHang(search);
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        List<DiaChi> listDiaChi = diaChiService.findAll();
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listHoaDon", list);
        model.addAttribute("contentPage", "../don-hang/hien-thi.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        HoaDon hoaDon = hoaDonService.findById(id);
        model.addAttribute("donHang", hoaDon);
        model.addAttribute("listHoaDonChiTiet", listHoaDonChiTiet);
        model.addAttribute("contentPage", "../don-hang/don-hang-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/xuat-pdf/{id}")
    public ResponseEntity<byte[]> xuatPDF(@PathVariable("id") UUID id) {
        ResponseEntity<byte[]> responseEntity = hoaDonService.generatePdfDonOnline(id);
        byte[] pdfBytes = responseEntity.getBody();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "hoa_don_"+id+".pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id) {
        List<NhanVien> listNhanVien = nhanVienService.nhanVienThanhToan(id);
        List<KhachHang> listKhachHang = khachHangService.khachHangThanhToan(id);
        List<DiaChi> listDiaChi = diaChiService.diaChiThanhToan(hoaDonService.findById(id).getKhachHang().getId());
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        HoaDon hoaDon = hoaDonService.findById(id);
        hoaDonnn = hoaDon;
        model.addAttribute("donHang", hoaDon);
        model.addAttribute("listHoaDonChiTiet", listHoaDonChiTiet);
        model.addAttribute("contentPage", "../don-hang/don-hang-view-update.jsp");
        return "home/layout";
    }

    @ResponseBody
    @GetMapping("/search-hdct")
    public List<HoaDonChiTiet> searchHDCT(Model model, @RequestParam("search") String ten, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        List<QuyDoi> listQuyDoi = quyDoiService.findAll();
        List<HoaDonChiTiet> listHoaDonChiTiets = hoaDonChiTietService.search(idHoaDon, ten);

        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listQuyDoi", listQuyDoi);
        model.addAttribute("hoaDon", hoaDon);
        return listHoaDonChiTiets;
    }

    @ResponseBody
    @GetMapping("/search-hdct-update")
    public List<HoaDonChiTiet> searchHDCTUpdate(Model model, @RequestParam("search") String ten, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        List<QuyDoi> listQuyDoi = quyDoiService.findAll();
        List<HoaDonChiTiet> listHoaDonChiTiets = hoaDonChiTietService.search(idHoaDon, ten);

        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listQuyDoi", listQuyDoi);
        model.addAttribute("hoaDon", hoaDon);
        return listHoaDonChiTiets;
    }

    @PostMapping("/update/{id}")
    public String Update(Model model, @PathVariable("id") UUID id,
                         @ModelAttribute("donHang") HoaDon hoaDon) {
        if (hoaDon.getTinhTrang() == 2) {
            hoaDon.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDon.setKhachHang(hoaDonnn.getKhachHang());
            hoaDon.setLoai(hoaDonnn.getLoai());
            hoaDon.setHinhThucThanhToan(hoaDonnn.getHinhThucThanhToan());
            LocalDate ngayCapNhat = LocalDate.now();
            List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDon.getId());
            if (!list.isEmpty()) {
                for (HoaDonChiTiet hdct : list
                ) {
                    long millis = System.currentTimeMillis();
                    Date date = new Date(millis);
                    imeiService.updatImei(date, hdct.getId());
                    hdct.setTinhTrang(1);
                    hoaDonChiTietService.update(hdct.getId(), hdct);
                }
                hoaDon.setTinhTrang(2);
                hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                hoaDonService.update(id, hoaDon);
            } else {
                hoaDon.setTinhTrang(2);
                hoaDon.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                hoaDonService.update(id, hoaDon);
            }
        } else {
            hoaDon.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDon.setKhachHang(hoaDonnn.getKhachHang());
            hoaDon.setId(id);
            hoaDon.setNgayCapNhat(Date.valueOf(LocalDate.now()));
            hoaDon.setNgayTao(hoaDonnn.getNgayTao());
            hoaDon.setLoai(hoaDonnn.getLoai());
            hoaDon.setHinhThucThanhToan(hoaDonnn.getHinhThucThanhToan());
            hoaDonService.update(id, hoaDon);
        }
        model.addAttribute("listKhachHang", khachHangService.findAll0());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listDiaChi", diaChiService.findAll0());
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId()));
        return "redirect:/don-hang/hien-thi";
    }

    @GetMapping("/xac-nhan/{id}")
    public String xacNhan(Model model, @PathVariable("id") UUID id) {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTinhTrang(1);
        hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        hd.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hoaDonService.update(id, hd);
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        if (!listHoaDonChiTiet.isEmpty()) {
            for (HoaDonChiTiet hdct : listHoaDonChiTiet
            ) {
                System.out.println(hdct.getId());
                imeiService.updatImeiChoXuLy(Date.valueOf(LocalDate.now()), hdct.getImei().getId());
            }
        }
        return "redirect:/don-hang/hien-thi";
    }
}
