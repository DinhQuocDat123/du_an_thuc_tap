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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

@RequestMapping("hoa-don")
@Controller
public class HoaDonController {
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

    private int dem = 0;
    private UUID idHoaDon = null;
    BigDecimal total = BigDecimal.ZERO;
    private HoaDon hoaDonnn = new HoaDon();
    private int loai = 0;
    private int httt = 0;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                          @RequestParam("pageNum") Optional<Integer> pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        dem = 0;
        List<HoaDon> page = hoaDonService.hoaDon();
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("dem", dem);
        model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
        model.addAttribute("listHoaDon", page);
        return "home/layout";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                         @RequestParam("search") String search,
                         @RequestParam(name = "soTienQuyDoi", required = false) BigDecimal soTienQuyDoi,
                         @RequestParam("pageNum") Optional<Integer> pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

        dem = 1;
        model.addAttribute("dem", dem);
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        Page<HoaDon> page = hoaDonService.getAll(pageable);
        List<HoaDon> list = hoaDonService.search(search, soTienQuyDoi);
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        List<DiaChi> listDiaChi = diaChiService.findAll();
        model.addAttribute("dem", dem);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listHoaDon", list);
        model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
        return "home/layout";
    }

    @GetMapping("/huy/{id}")
    public String huyHoaDon(Model model, @PathVariable("id") UUID id
            , @ModelAttribute("hoaDon") HoaDon hoaDon
    ) {
        HoaDon hd = hoaDonService.findById(id);
        LocalDate ngayCapNhat = LocalDate.now();
        List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
        if (!list.isEmpty()) {
            for (HoaDonChiTiet hdct : list
            ) {
                ChiTietSanPham ctsp = chiTietSanPhamService.getChiTiet(hdct.getImei().getId());
                ctsp.setSoLuong(ctsp.getSoLuong() + 1);
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
                ctsp.setNgayTao(date);
                if (ctsp.getSoLuong() > 0) {
                    ctsp.setTinhTrang(0);
                }
                chiTietSanPhamService.update1(ctsp);
                imeiService.updatImei1(date, hdct.getId());
                hdct.setTinhTrang(8);
                hoaDonChiTietService.update(hdct.getId(), hdct);
            }
            hd.setTinhTrang(8);
            hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDonService.update(id, hd);
        } else {
            hd.setTinhTrang(8);
            hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
            hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDonService.update(id, hd);
        }
        model.addAttribute("hoaDon", hd);
        model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
        return "redirect:/hoa-don/hien-thi"; // Chuyển hướng về trang danh sách hóa đơn sau khi hủy
    }

    @PostMapping("/loc")
    public String loc1(Model model,
                       @RequestParam(value = "nhanVien", required = false) UUID idNV,
                       @RequestParam(value = "khachHang", required = false) UUID idKH,
                       @RequestParam(value = "diaChi", required = false) UUID idDC,
                       @RequestParam(value = "trangThai", required = false) Integer trangThai,
                       @RequestParam(value = "loaiHoaDon", required = false) Integer loaiHoaDon,
                       @RequestParam(value = "startDate", required = false) String startDate,
                       @RequestParam(value = "receiveStartDate", required = false) String receiveStartDate,
                       @RequestParam(value = "shipStartDate", required = false) String shipStartDate,
                       @RequestParam(value = "endDate", required = false) String endDate,
                       @RequestParam(value = "receiveEndDate", required = false) String receiveEndDate,
                       @RequestParam(value = "shipEndDate", required = false) String shipEndDate,
                       @ModelAttribute("nhanVien") NhanVien nhanVien,
                       @ModelAttribute("khachHang") KhachHang khachHang,
                       @ModelAttribute("diaChi") DiaChi diaChi,
                       @ModelAttribute("hoaDon") HoaDon hoaDon,
                       @RequestParam("pageNum") Optional<Integer> pageNum,
                       @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), 5);
        dem = 2;
        if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() &&
                receiveEndDate.isEmpty() && shipEndDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, trangThai, loaiHoaDon,
                    null, null, null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        } else if (receiveStartDate.isEmpty() && shipStartDate.isEmpty() && receiveEndDate.isEmpty() && shipEndDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, trangThai, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, trangThai, loaiHoaDon,
                    null, null, null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty() && startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, trangThai, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        } else if (shipEndDate.isEmpty() && shipStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, trangThai, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), null, null, Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        } else if (receiveEndDate.isEmpty() && receiveStartDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, trangThai, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), null, null
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        } else if (startDate.isEmpty() && endDate.isEmpty()) {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, trangThai, loaiHoaDon,
                    null, null, Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        } else {
            List<HoaDon> listHoaDon = hoaDonService.loc1(idKH, idNV, idDC, trangThai, loaiHoaDon,
                    Date.valueOf(startDate), Date.valueOf(endDate), Date.valueOf(shipStartDate), Date.valueOf(shipEndDate), Date.valueOf(receiveStartDate), Date.valueOf(receiveEndDate)
            );
            model.addAttribute("listHoaDon", listHoaDon);
            model.addAttribute("dem", dem);
            model.addAttribute("listKhachHang", khachHangService.findAll00());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listDiaChi", diaChiService.getALL0());
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "home/layout";
        }
    }


    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        List<DiaChi> listDiaChi = diaChiService.findAll();
        List<QuyDoi> listQuyDoi = quyDoiService.findAll();
        dem = 4;
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listQuyDoi", listQuyDoi);
        model.addAttribute("dem", dem);
        HoaDon hoaDon = hoaDonService.findById(id);
        loai = hoaDon.getLoai();
        httt = hoaDon.getHinhThucThanhToan();
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("listHoaDonChiTiet", listHoaDonChiTiet);
        model.addAttribute("contentPage", "../hoadon/hoa-don-detail.jsp");
        return "home/layout";
    }

    @GetMapping("/xuat-pdf/{id}")
    public ResponseEntity<byte[]> xuatPDF(@PathVariable("id") UUID id) {
        ResponseEntity<byte[]> responseEntity = hoaDonService.generatePdfDonTaiQuay(id);
        byte[] pdfBytes = responseEntity.getBody();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "hoa_don_"+id+".pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }


    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon,
                          @ModelAttribute("khachHang") KhachHang khachHang,
                          @ModelAttribute("nhanVien") NhanVien nhanVien,
                          @ModelAttribute("diaChi") DiaChi diaChi
//                          @ModelAttribute("quyDoi") QuyDoi quyDoi
    ) {

        hoaDon.setTinhTrang(0);
        List<KhachHang> listKhachHang = khachHangService.findAll0();
        model.addAttribute("listKhachHang", listKhachHang);

        List<NhanVien> listNhanVien = nhanVienService.findAll();
        model.addAttribute("listNhanVien", listNhanVien);
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        model.addAttribute("listDiaChi", listDiaChi);

//        List<QuyDoi> listQuyDoi = quyDoiService.findAll();
//        model.addAttribute("listQuyDoi", listQuyDoi);
//        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("contentPage", "../hoadon/hoa-don-cho.jsp");

        return "home/layout";
    }

    @GetMapping("/add")
    public String addHoaDon(Model model
    ) {
        List<HoaDon> list = hoaDonService.find();
        if (list.size() > 6) {
            return "redirect:/hoa-don/hien-thi";
        } else {
            Integer hd = hoaDonService.findAll().size() + 1;
            String maHD = "";
            if (hd < 9) {
                maHD = "HD0" + hd;
            } else {
                maHD = "HD" + hd;
            }
            NhanVien nhanVien = nhanVienService.findById(SecurityUtil.getId().getId());
            HoaDon hdc = new HoaDon();
            hdc.setMa(maHD);
            hdc.setTinhTrang(0);
            hdc.setLoai(0);
            hdc.setNgayTao(Date.valueOf(LocalDate.now()));
            hdc.setNhanVien(nhanVien);
            hoaDonService.add(hdc);
            model.addAttribute("contentPage", "../hoadon/hoa-don.jsp");
            return "redirect:/hoa-don/hien-thi";
        }
    }

    //update thông tin khi trạng thái là đang chờ, chờ xác nhận...
    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll();
        List<DiaChi> listDiaChi = diaChiService.findAll();
        List<QuyDoi> listQuyDoi = quyDoiService.findAll();
        dem = 4;
        idHoaDon = id;
        List<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTiet(id);
        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listQuyDoi", listQuyDoi);
        HoaDon hoaDon = hoaDonService.findById(id);
        loai = hoaDonnn.getLoai();
        httt = hoaDonnn.getHinhThucThanhToan();
        hoaDonnn = hoaDon;
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("listHoaDonChiTiet", listHoaDonChiTiet);
        model.addAttribute("contentPage", "../hoadon/hoa-don-view-update.jsp");
        return "home/layout";
    }

    //
    @PostMapping("/update/{id}")
    public String Update(Model model, @PathVariable("id") UUID id,
                         @ModelAttribute("hoaDon") HoaDon hoaDon) {
        if (hoaDon.getTinhTrang() == 8) {
            HoaDon hd = hoaDonService.findById(id);
            hd.setNhanVien(nhanVienService.nhanVienUpdateHoaDon(id));
            hd.setLoai(loai);
            hd.setHinhThucThanhToan(httt);
            LocalDate ngayCapNhat = LocalDate.now();
            List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
            if (!list.isEmpty()) {
                for (HoaDonChiTiet hdct : list
                ) {
                    ChiTietSanPham ctsp = chiTietSanPhamService.getChiTiet(hdct.getImei().getId());
                    ctsp.setSoLuong(ctsp.getSoLuong() + 1);
                    long millis = System.currentTimeMillis();
                    Date date = new Date(millis);
                    ctsp.setNgayTao(date);
                    if (ctsp.getSoLuong() > 0) {
                        ctsp.setTinhTrang(0);
                    }
                    chiTietSanPhamService.update1(ctsp);
                    imeiService.updatImei1(date, hdct.getId());
                    hdct.setTinhTrang(8);
                    hoaDonChiTietService.update(hdct.getId(), hdct);
                }
                hd.setTinhTrang(8);
                hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hoaDonService.update(id, hd);
                return "redirect:/hoa-don/hien-thi";
            } else {
                hd.setTinhTrang(8);
                hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hoaDonService.update(id, hd);
                return "redirect:/hoa-don/hien-thi";
            }
        } else if (hoaDon.getTinhTrang() == 2) {
            HoaDon hd = hoaDonService.findById(id);
            hd.setNhanVien(nhanVienService.nhanVienUpdateHoaDon(id));
            LocalDate ngayCapNhat = LocalDate.now();
            List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hd.getId());
            if (!list.isEmpty()) {
                for (HoaDonChiTiet hdct : list
                ) {
                    long millis = System.currentTimeMillis();
                    Date date = new Date(millis);
                    imeiService.updatImei(date, hdct.getId());
                    hdct.setTinhTrang(1);
                    hoaDonChiTietService.update(hdct.getId(), hdct);
                }
                hd.setTinhTrang(2);
                hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hoaDonService.update(id, hd);
            } else {
                hd.setTinhTrang(2);
                hd.setNgayCapNhat(Date.valueOf(ngayCapNhat));
                hd.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
                hoaDonService.update(id, hd);
            }
        } else {
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            hoaDon.setId(id);
            hoaDon.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
            hoaDon.setNgayCapNhat(Date.valueOf(LocalDate.now()));
            hoaDon.setNgayTao(date);
            hoaDonService.update(id, hoaDon);
        }
        model.addAttribute("listKhachHang", khachHangService.findAll0());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listDiaChi", diaChiService.findAll0());
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId()));
        model.addAttribute("contentPage", "../hoadon/hoa-don-view-update.jsp");
        return "home/layout";
    }

    @ResponseBody
    @GetMapping("/search-hdct")
    public List<HoaDonChiTiet> searchHDCT(Model model, @RequestParam("search") String ten, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        List<NhanVien> listNhanVien = nhanVienService.findAll();
        List<KhachHang> listKhachHang = khachHangService.findAll00();
        List<DiaChi> listDiaChi = diaChiService.getALL0();
        List<QuyDoi> listQuyDoi = quyDoiService.findAll();
        dem = 5;
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
        dem = 5;
        List<HoaDonChiTiet> listHoaDonChiTiets = hoaDonChiTietService.search(idHoaDon, ten);

        model.addAttribute("listKhachHang", listKhachHang);
        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listQuyDoi", listQuyDoi);
        model.addAttribute("hoaDon", hoaDon);
        return listHoaDonChiTiets;
    }

    @GetMapping("/delete-hoa-don-chi-tiet/{id}")
    public String deleteHDCT(Model model, @PathVariable("id") UUID id,
                             @ModelAttribute("HoaDon") HoaDon hoaDon) {

        HoaDonChiTiet hd = hoaDonChiTietService.findById(id);
        ChiTietSanPham ct = chiTietSanPhamService.getChiTiet(hd.getImei().getId());
        ct.setSoLuong(ct.getSoLuong() + 1);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        ct.setNgayCapNhat(date);
        if (ct.getSoLuong() > 0) {
            ct.setTinhTrang(0);
        }
        chiTietSanPhamService.update1(ct);
        imeiService.updatImei1(date, id);
        hoaDonChiTietService.delete(id);
        List<HoaDonChiTiet> list = hoaDonChiTietService.getHoaDonChiTiet(hoaDonnn.getId());
        if (list.isEmpty()) {
            hoaDonnn.setTongTien(BigDecimal.ZERO);
            hoaDonnn.setTinhTrang(0);
            hoaDonnn.setMa(hoaDonnn.getMa());
            hoaDonnn.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        } else {
            // Tính tổng đơn giá trong list hóa đơn chi tiết còn lại
            BigDecimal subTotal = list.stream()
                    .map(hdd -> hdd.getDonGia())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // Set tổng tiền bằng tổng đơn giá trong list hóa đơn chi tiết còn lại
            total = subTotal;
            hoaDonnn.setTongTien(subTotal);
            hoaDonnn.setNhanVien(nhanVienService.findById(SecurityUtil.getId().getId()));
        }
        hoaDonService.update(hoaDonnn.getId(), hoaDonnn);
        model.addAttribute("tong", String.valueOf(total));
        model.addAttribute("listHoaDonChiTiet", list);
        model.addAttribute("hoaDon", hoaDonnn);
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll0());
        model.addAttribute("listDiaChi", diaChiService.findAll0());
        return "redirect:/hoa-don/view-update/" + idHoaDon;
    }

}
