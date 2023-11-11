package com.example.demo.controllers;

import com.example.demo.models.SanPhamGiamGia;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.KhuyenMaiService;
import com.example.demo.services.SanPhamGiamGiaService;
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

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/san-pham-giam-gia")
public class SanPhamGiamGiaController {
    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    KhuyenMaiService khuyenMaiService;
    @Autowired
    SanPhamGiamGiaService sanPhamGiamGiaService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 5);

        Page<SanPhamGiamGia> sanPhamGG = sanPhamGiamGiaService.getSPGGOn(pageable);
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll());
        model.addAttribute("listKM", khuyenMaiService.findAll());
        model.addAttribute("total", sanPhamGG.getTotalPages());
        model.addAttribute("listSpgg", sanPhamGG.getContent());
        model.addAttribute("size", sanPhamGG.getSize());
        model.addAttribute("page", sanPhamGG.getNumber());
        model.addAttribute("sanphamgiamgia", new SanPhamGiamGia());
        model.addAttribute("contentPage", "../san-pham-giam-gia/index.jsp");
        return "home/layout";

    }

    @GetMapping("/hien-thi-da-xoa")
    public String hienThiDaXoa(Model model, @RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 5);

        Page<SanPhamGiamGia> sanPhamGG = sanPhamGiamGiaService.getSPGGOff(pageable);
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll());
        model.addAttribute("listKM", khuyenMaiService.findAll());
        model.addAttribute("total", sanPhamGG.getTotalPages());
        model.addAttribute("listSpgg", sanPhamGG.getContent());
        model.addAttribute("size", sanPhamGG.getSize());
        model.addAttribute("page", sanPhamGG.getNumber());
        model.addAttribute("contentPage", "../san-pham-giam-gia/san-pham-giam-gia-da-xoa.jsp");
        return "home/layout";

    }

    @GetMapping("/khoi-phuc-tat-ca")
    public String khoiPhucAll() {
        List<SanPhamGiamGia> list = sanPhamGiamGiaService.getSPGGOff();
        for (SanPhamGiamGia spgg : list) {
            spgg.setTinhTrang(0);
            sanPhamGiamGiaService.add(spgg);
        }
        return "redirect:/san-pham-giam-gia/hien-thi-da-xoa";
    }

    @PostMapping("/search-on")
    public String searchOn(Model model, @RequestParam("search") String search) {
        List<SanPhamGiamGia> list = sanPhamGiamGiaService.searchSPGGOn(search);

        model.addAttribute("listCTSP", chiTietSanPhamService.findAll());
        model.addAttribute("listSpgg", list);
        model.addAttribute("listKM", khuyenMaiService.findAll());
        model.addAttribute("sanphamgiamgia", new SanPhamGiamGia());

        model.addAttribute("contentPage", "../san-pham-giam-gia/index.jsp");
        return "home/layout";

    }

    @PostMapping("/search-off")
    public String searchOff(Model model, @RequestParam("search1") String search) {
        List<SanPhamGiamGia> list = sanPhamGiamGiaService.searchSPGGOff(search);

        model.addAttribute("listCTSP", chiTietSanPhamService.findAll());
        model.addAttribute("listSpgg", list);
        model.addAttribute("listKM", khuyenMaiService.findAll());

        model.addAttribute("contentPage", "../san-pham-giam-gia/san-pham-giam-gia-da-xoa.jsp");
        return "home/layout";

    }

    @GetMapping("/add")
    public String viewAd(Model model, @ModelAttribute("sanphamgiamgia") SanPhamGiamGia sanPhamGiamGia) {
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll());
        model.addAttribute("listKM", khuyenMaiService.findAll());
        model.addAttribute("sanphamgiamgia", new SanPhamGiamGia());
        model.addAttribute("contentPage", "../san-pham-giam-gia/add-san-pham-giam-gia.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "sanphamgiamgia") SanPhamGiamGia sanPhamGiamGia,
                      BindingResult result, Model model, @RequestParam(value = "page", defaultValue = "0", required = false) Integer pageNum) {
        if (result.hasErrors()) {
            Pageable pageable = PageRequest.of(pageNum, 5);

            Page<SanPhamGiamGia> sanPhamGG = sanPhamGiamGiaService.getSPGGOn(pageable);
            model.addAttribute("listCTSP", chiTietSanPhamService.findAll());
            model.addAttribute("listKM", khuyenMaiService.findAll());
            model.addAttribute("total", sanPhamGG.getTotalPages());
            model.addAttribute("listSpgg", sanPhamGG.getContent());
            model.addAttribute("size", sanPhamGG.getSize());
            model.addAttribute("page", sanPhamGG.getNumber());
            model.addAttribute("contentPage", "../san-pham-giam-gia/index.jsp");
            return "home/layout";
        }
        sanPhamGiamGia.setTinhTrang(0);
        sanPhamGiamGiaService.add(sanPhamGiamGia);
        return "redirect:/san-pham-giam-gia/hien-thi";


    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable("id") UUID id) {

        sanPhamGiamGiaService.delete(id);
        return "redirect:/san-pham-giam-gia/hien-thi";

    }

    @GetMapping("/khoi-phuc/{id}")
    public String khoiPhuc(@PathVariable("id") UUID id) {

        SanPhamGiamGia sanPhamGiamGia=sanPhamGiamGiaService.findById(id);
        sanPhamGiamGia.setTinhTrang(0);
        sanPhamGiamGiaService.add(sanPhamGiamGia);
        return "redirect:/san-pham-giam-gia/hien-thi-da-xoa";

    }

    @GetMapping("/update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id, @ModelAttribute("sanphamgiamgiaupdate") SanPhamGiamGia sanPhamGiamGia) {
        model.addAttribute("listCTSP", chiTietSanPhamService.findAll());
        model.addAttribute("listKM", khuyenMaiService.findAll());
        SanPhamGiamGia sanPhamGiamGia1 = sanPhamGiamGiaService.findById(id);

        model.addAttribute("sanphamgiamgiaupdate", sanPhamGiamGia1);
        model.addAttribute("contentPage", "../san-pham-giam-gia/update-san-pham-giam-gia.jsp");
        return "home/layout";

    }

    @PostMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") UUID id, @Valid @ModelAttribute("sanphamgiamgiaupdate") SanPhamGiamGia sanPhamGiamGia,
                         BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("listCTSP", chiTietSanPhamService.findAll());
            model.addAttribute("listKM", khuyenMaiService.findAll());

            model.addAttribute("contentPage", "../san-pham-giam-gia/update-san-pham-giam-gia.jsp");
            return "home/layout";
        }


        sanPhamGiamGiaService.update(id, sanPhamGiamGia);
        return "redirect:/san-pham-giam-gia/hien-thi";


    }
}