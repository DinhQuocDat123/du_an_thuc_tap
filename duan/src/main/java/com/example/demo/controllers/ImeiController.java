package com.example.demo.controllers;

import com.example.demo.models.IMEI;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.IMEIService;
import com.example.demo.util.QRCodeGenerator;
import com.google.zxing.WriterException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/imei")
public class ImeiController {
    @Autowired
    IMEIService imeiService;

    QRCodeGenerator qrCodeGenerator;
    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;
    private Date ngay;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        List<IMEI> imeiPage = imeiService.getImeiOn();
        model.addAttribute("listImei", imeiPage);
        model.addAttribute("contentPage", "../imei/index.jsp");
        return "home/layout";

    }

    @PostMapping("/search-on")
    public String searchOn(Model model, @RequestParam("search") String search) {
        List<IMEI> lissearch = imeiService.searchOn(search);
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listImei", lissearch);
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../imei/index.jsp");
        return "home/layout";

    }

    @PostMapping("/search-off-1")
    public String searchOff1(Model model, @RequestParam("search") String search) {
        List<IMEI> lissearch = imeiService.searchOff(search);
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listImei", lissearch);
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../imei/imei-da-ban.jsp");
        return "home/layout";
    }

    @PostMapping("/search-off-2")
    public String searchOff2(Model model, @RequestParam("search") String search) {
        List<IMEI> lissearch = imeiService.searchOff2(search);
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("listImei", lissearch);
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../imei/imei-da-xoa.jsp");
        return "home/layout";
    }


    @GetMapping("/hien-thi-da-ban")
    public String hienThiDaBan(Model model) {
        List<IMEI> imeiPage = imeiService.getImeiOfff();
        model.addAttribute("listImei", imeiPage);
        model.addAttribute("contentPage", "../imei/imei-da-ban.jsp");
        return "home/layout";
    }

    @GetMapping("/hien-thi-da-xoa")
    public String hienThiDaXoa(Model model) {
        List<IMEI> imeiPage = imeiService.getImeiOff3();
        model.addAttribute("listImei", imeiPage);
        model.addAttribute("contentPage", "../imei/imei-da-xoa.jsp");
        return "home/layout";
    }

    @GetMapping("/khoi-phuc/{id}")
    public String khoiPhuc(@PathVariable("id") UUID id) {
        IMEI imei = imeiService.findById(id);
        imei.setTinhTrang(0);
        imeiService.add(imei);
        return "redirect:/imei/hien-thi-da-xoa";
    }

    @GetMapping("/khoi-phuc-tat-ca")
    public String khoiPhucAll() {
        List<IMEI> list = imeiService.findAll3();
        for (IMEI imei : list) {
            imei.setTinhTrang(0);
            imeiService.add(imei);
        }
        return "redirect:/imei/hien-thi-da-xoa";
    }

    @GetMapping("/view-add")
    public String viewAd(Model model, @ModelAttribute("imei") IMEI imei) {
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        model.addAttribute("imei", new IMEI());
        String ma = "IMEI" + imeiService.findAll().size();
        model.addAttribute("ma", ma);
        model.addAttribute("contentPage", "../imei/add-imei.jsp");

        return "home/layout";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "imei") IMEI imei,
                      BindingResult result, Model model) throws IOException, WriterException {

        if (result.hasErrors()||imei.getChiTietSanPham()==null) {

            model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
            String ma = "IMEI" + imeiService.findAll().size();
            model.addAttribute("ma", ma);
            model.addAttribute("tb", "Hãy chọn sản phẩm!");
            model.addAttribute("contentPage", "../imei/add-imei.jsp");
            return "home/layout";

        }
        String ma = "IMEI" + (imeiService.findAll().size() + 1);
        imei.setMa(ma);
        List<IMEI> listcheck = imeiService.findAll();
        for (IMEI i : listcheck
        ) {
            if (i.getSoImei().equalsIgnoreCase(imei.getSoImei())) {
                model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
                model.addAttribute("thongBao", "IMEI này đã có trong dữ liệu");
                model.addAttribute("ma", ma);
                model.addAttribute("contentPage", "../imei/add-imei.jsp");
                return "home/layout";
            }
        }
        String projectRootPath = System.getProperty("user.dir");
        String outputFolderPath = projectRootPath + "/src/main/webapp/maqr";
        QRCodeGenerator.generatorQRCode(imei, outputFolderPath);
        imei.setMaQr(imei.getSoImei() + ".png");

        LocalDate localDate = LocalDate.now();
        imei.setNgayTao(Date.valueOf(localDate));

        imeiService.add(imei);
        return "redirect:/imei/hien-thi";


    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable("id") UUID id) {
        imeiService.delete(id);
        return "redirect:/imei/hien-thi";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("imeiupdate") IMEI imei) {
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll0());
        IMEI imei1 = imeiService.findById(id);
        model.addAttribute("imeiupdate", imei1);
        model.addAttribute("contentPage", "../imei/update-imei.jsp");
        ngay = Date.valueOf(imei1.getNgayTao().toString());
        return "home/layout";


    }

    @PostMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("imeiupdate") IMEI imei,
                         BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("listCTSP", chiTietSanPhamService.findAll());
            model.addAttribute("contentPage", "../imei/update-imei.jsp");
            return "home/layout";
        }
        IMEI imei1 = imeiService.findById(id);
        imei1.setMa(imei.getMa());
        imei1.setNgayTao(ngay);
        imei1.setChiTietSanPham(imei.getChiTietSanPham());
        imei1.setSoImei(imei.getSoImei());
        imei1.setMoTa(imei.getMoTa());
        LocalDate localDate = LocalDate.now();
        imei1.setNgayCapNhat(Date.valueOf(localDate));
        imei1.setTinhTrang(imei.getTinhTrang());
        imeiService.update(id, imei1);
        return "redirect:/imei/hien-thi";
    }

    @ResponseBody
    @GetMapping("/show-qr/{id}")
    public List<IMEI> showQR(Model model, @PathVariable("id") UUID id) {
        List<IMEI> list = imeiService.showIMEI(id);
        System.out.println(1);
        return list;
    }
}
