package com.example.demo.controllers;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.Chip;
import com.example.demo.models.IMEI;
import com.example.demo.models.MauSac;
import com.example.demo.models.Pin;
import com.example.demo.models.Ram;
import com.example.demo.models.Rom;
import com.example.demo.models.SanPham;
import com.example.demo.services.CameraService;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.ChipService;
import com.example.demo.services.DungLuongPinService;
import com.example.demo.services.HangSanPhamService;
import com.example.demo.services.IMEIService;
import com.example.demo.services.ManHinhService;
import com.example.demo.services.MauSacService;
import com.example.demo.services.PinService;
import com.example.demo.services.RamService;
import com.example.demo.services.RomService;
import com.example.demo.services.SanPhamService;
import com.example.demo.util.FileUploadUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/chi-tiet-san-pham")

@Controller
public class ChiTietSanPhamController {
    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    SanPhamService sanPhamService;
    @Autowired
    MauSacService mauSacService;
    @Autowired
    ChipService chipService;
    @Autowired
    RamService ramService;
    @Autowired
    RomService romService;
    @Autowired
    PinService pinService;
    @Autowired
    HangSanPhamService hangSanPhamService;
    @Autowired
    DungLuongPinService dungLuongPinService;
    @Autowired
    ManHinhService manHinhService;
    @Autowired
    CameraService cameraService;
    @Autowired
    IMEIService imeiService;

    private Date ngay;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("pageNum") Optional<Integer> pageNum
    ) {
        List<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamService.getAllTTOn();
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listCTSP", chiTietSanPhamPage);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-da-xoa")
    public String hienThiDaXoa(Model model, @RequestParam("pageNum") Optional<Integer> pageNum
    ) {
        List<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamService.getAllTTOff();
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listCTSP", chiTietSanPhamPage);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/san-pham-da-xoa.jsp");
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute(name = "Pin") Pin pin,
                          @ModelAttribute(name = "chip") Chip chip,
                          @ModelAttribute(name = "ram") Ram ram,
                          @ModelAttribute(name = "mauSac") MauSac mauSac,
                          @ModelAttribute(name = "rom") Rom rom,
                          @ModelAttribute(name = "sanPham") SanPham sanPham,
                          @ModelAttribute(name = "chitietsanpham") ChiTietSanPham chiTietSanPham) {

        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");


        return "home/layout";


    }


    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "chitietsanpham") ChiTietSanPham chiTietSanPham,
                      BindingResult result, Model model, @RequestParam("images") MultipartFile multipartFile) throws IOException {

        if (result.hasErrors()) {


            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());


            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("contentPage", "../chi-tiet-san-pham/add-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "src/main/webapp/uploads/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        chiTietSanPham.setUrlAnh(fileName);
        LocalDate localDate = LocalDate.now();
        chiTietSanPham.setNgayTao(Date.valueOf(localDate));
        chiTietSanPham.setTinhTrang(0);
        chiTietSanPhamService.add(chiTietSanPham);
        return "redirect:/chi-tiet-san-pham/hien-thi";


    }

    @GetMapping("/view-update/{id}")
    public String viewupdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("chitietsanphamupdate") ChiTietSanPham chiTietSanPham,
                             @ModelAttribute(name = "Pin") Pin pin,
                             @ModelAttribute(name = "chip") Chip chip,
                             @ModelAttribute(name = "ram") Ram ram,
                             @ModelAttribute(name = "mauSac") MauSac mauSac,
                             @ModelAttribute(name = "rom") Rom rom,
                             @ModelAttribute(name = "sanPham") SanPham sanPham) {

        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());

        ChiTietSanPham chiTietSanPham1 = chiTietSanPhamService.findById(id);
        ngay = Date.valueOf(chiTietSanPham1.getNgayTao().toString());

        model.addAttribute("chitietsanphamupdate", chiTietSanPham1);
        model.addAttribute("ctsp", chiTietSanPham1);
        model.addAttribute("contentPage", "../chi-tiet-san-pham/update-chi-tiet-san-pham.jsp");

        return "home/layout";


    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") UUID id, @Valid @ModelAttribute("chitietsanphamupdate") ChiTietSanPham chiTietSanPham,
                         BindingResult result,
                         @RequestParam("anh1s") MultipartFile anh1,
                         @RequestParam("checkanh1") String checkanh1,
                         Model model,
                         @ModelAttribute(name = "Pin") Pin pin,
                         @ModelAttribute(name = "Chip") Chip chip,
                         @ModelAttribute(name = "Ram") Ram ram,
                         @ModelAttribute(name = "MauSac") MauSac mauSac,
                         @ModelAttribute(name = "Rom") Rom rom,
                         @ModelAttribute(name = "SanPham") SanPham sanPham) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("Pin", new Pin());
            model.addAttribute("chip", new Chip());
            model.addAttribute("ram", new Ram());
            model.addAttribute("rom", new Rom());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("mauSac", new MauSac());


            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            ChiTietSanPham chiTietSanPham1 = chiTietSanPhamService.findById(id);
//            model.addAttribute("chitietsanphamupdate",chiTietSanPham1);
            model.addAttribute("contentPage", "../chi-tiet-san-pham/update-chi-tiet-san-pham.jsp");

            return "home/layout";
        }
        String fileName1 = StringUtils.cleanPath(anh1.getOriginalFilename());
        if (checkanh1.equals("cu1")) {
        } else {
            if (fileName1.equals("")) {
            } else {
                String uploadDir = "src/main/webapp/uploads/";
                FileUploadUtil.saveFile(uploadDir, fileName1, anh1);
            }
            chiTietSanPham.setUrlAnh(fileName1);
        }

        LocalDate localDate = LocalDate.now();
        chiTietSanPham.setNgayCapNhat(Date.valueOf(localDate));
        chiTietSanPham.setNgayTao(ngay);
        chiTietSanPham.setTinhTrang(0);


        chiTietSanPhamService.update(id, chiTietSanPham);
        System.out.println(chiTietSanPham.getNgayTao());
        return "redirect:/chi-tiet-san-pham/hien-thi";

    }


    @GetMapping("/delete/{id}")
    public String remove(@PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, Model model) {
        List<IMEI> listImei = imeiService.statusCTSP(id);
        if (listImei.isEmpty()) {
            ChiTietSanPham chiTietSanPham = chiTietSanPhamService.findById(id);
            chiTietSanPham.setTinhTrang(1);
            chiTietSanPhamService.add(chiTietSanPham);
            List<ChiTietSanPham> page = chiTietSanPhamService.getAllTTOn();
            model.addAttribute("listCTSP", page);
            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
            return "redirect:/chi-tiet-san-pham/hien-thi";
        } else {
            model.addAttribute("thongBao", "Sản phẩm này còn hàng tồn, không thể đổi trạng thái");
            List<ChiTietSanPham> page = chiTietSanPhamService.getAllTTOn();
            model.addAttribute("listCTSP", page);
            model.addAttribute("listSanPham", sanPhamService.findAll0());
            model.addAttribute("listMauSac", mauSacService.findAll0());
            model.addAttribute("listChip", chipService.findAll0());
            model.addAttribute("listRam", ramService.findAll0());
            model.addAttribute("listRom", romService.findAll0());
            model.addAttribute("listHang", hangSanPhamService.findAll0());
            model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
            model.addAttribute("listPin", pinService.findAll0());
            model.addAttribute("listManHinh", manHinhService.findAll0());
            model.addAttribute("listCamera", cameraService.findAll0());
            model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
            return "home/layout";
        }
    }

    @GetMapping("/khoi-phuc/{id}")
    public String khoiPhuc(@PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, Model model) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.findById(id);
        chiTietSanPham.setTinhTrang(0);
        chiTietSanPhamService.add(chiTietSanPham);
        List<ChiTietSanPham> page = chiTietSanPhamService.getAllTTOff();
        model.addAttribute("list", page);
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        return "redirect:/chi-tiet-san-pham/hien-thi-da-xoa";
    }

    @GetMapping("/khoi-phuc-tat-ca")
    public String khoiPhucAll(@RequestParam("pageNum") Optional<Integer> pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, Model model) {

        List<ChiTietSanPham> chiTietSanPhams = chiTietSanPhamService.finAllTTOff();
        for (ChiTietSanPham ctsp : chiTietSanPhams) {
            ctsp.setTinhTrang(0);
            ctsp.setNgayCapNhat(Date.valueOf(LocalDate.now()));
            chiTietSanPhamService.add(ctsp);
        }
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        Page<ChiTietSanPham> page = chiTietSanPhamService.getAll(pageable);
        model.addAttribute("total", page.getTotalPages());
        model.addAttribute("list", page.getContent());
        model.addAttribute("size", page.getSize());
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        model.addAttribute("page", page.getNumber());
        return "redirect:/chi-tiet-san-pham/hien-thi-da-xoa";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("search") String search,
                         @ModelAttribute("chitietsanpham") ChiTietSanPham chiTietSanPham,
                         @ModelAttribute(name = "Pin") Pin pin,
                         @ModelAttribute(name = "chip") Chip chip,
                         @ModelAttribute(name = "ram") Ram ram,
                         @ModelAttribute(name = "mauSac") MauSac mauSac,
                         @ModelAttribute(name = "rom") Rom rom,
                         @ModelAttribute(name = "sanPham") SanPham sanPham) {
        List<ChiTietSanPham> list = chiTietSanPhamService.search(search);
        model.addAttribute("listCTSP", list);
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        return "home/layout";
    }

    @PostMapping("/search-da-xoa")
    public String searchDaXoa(Model model, @RequestParam("search") String search,
                              @ModelAttribute("chitietsanpham") ChiTietSanPham chiTietSanPham,
                              @ModelAttribute(name = "Pin") Pin pin,
                              @ModelAttribute(name = "chip") Chip chip,
                              @ModelAttribute(name = "ram") Ram ram,
                              @ModelAttribute(name = "mauSac") MauSac mauSac,
                              @ModelAttribute(name = "rom") Rom rom,
                              @ModelAttribute(name = "sanPham") SanPham sanPham) {
        List<ChiTietSanPham> list = chiTietSanPhamService.search1(search);
        model.addAttribute("listCTSP", list);
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        return "home/layout";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize, Model model) {
        ChiTietSanPham chiTietSanPham1 = chiTietSanPhamService.findById(id);
        model.addAttribute("chitietsanpham", chiTietSanPham1);
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        Page<ChiTietSanPham> chiTietSanPhamPage = chiTietSanPhamService.getAll(pageable);
        model.addAttribute("total", chiTietSanPhamPage.getTotalPages());
        model.addAttribute("size", chiTietSanPhamPage.getSize());
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        model.addAttribute("page", chiTietSanPhamPage.getNumber());
        model.addAttribute("listCTSP", chiTietSanPhamPage.getContent());
        return "home/layout";

    }

    @PostMapping("/loc")
    public String loc(Model model, @RequestParam(value = "hang", required = false) UUID hang,
                      @RequestParam(value = "ram", required = false) UUID Ram,
                      @RequestParam(value = "rom", required = false) UUID Rom,
                      @RequestParam(value = "dungLuongPin", required = false) UUID dungLuongPin,
                      @RequestParam(value = "chip", required = false) UUID Chip,
                      @RequestParam(value = "manHinh", required = false) UUID moTaMan,
                      @RequestParam(value = "camera", required = false) UUID moTaCam,
                      @ModelAttribute("chitietsanpham") ChiTietSanPham chiTietSanPham,
                      @ModelAttribute(name = "Pin") Pin pin,
                      @ModelAttribute(name = "chip") Chip chip,
                      @ModelAttribute(name = "ram") Ram ram,
                      @ModelAttribute(name = "mauSac") MauSac mauSac,
                      @ModelAttribute(name = "rom") Rom rom,
                      @ModelAttribute(name = "sanPham") SanPham sanPham) {
        List<ChiTietSanPham> list = chiTietSanPhamService.loc(hang, Ram, Rom, dungLuongPin, Chip, moTaMan, moTaCam);
        model.addAttribute("listCTSP", list);
        model.addAttribute("listSanPham", sanPhamService.findAll0());
        model.addAttribute("listMauSac", mauSacService.findAll0());
        model.addAttribute("listChip", chipService.findAll0());
        model.addAttribute("listRam", ramService.findAll0());
        model.addAttribute("listRom", romService.findAll0());
        model.addAttribute("listHang", hangSanPhamService.findAll0());
        model.addAttribute("dungLuongPin", dungLuongPinService.findAll0());
        model.addAttribute("listPin", pinService.findAll0());
        model.addAttribute("listManHinh", manHinhService.findAll0());
        model.addAttribute("listCamera", cameraService.findAll0());
        model.addAttribute("contentPage", "../chi-tiet-san-pham/index.jsp");
        return "home/layout";
    }
}
