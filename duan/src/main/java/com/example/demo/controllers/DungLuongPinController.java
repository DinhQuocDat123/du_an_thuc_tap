package com.example.demo.controllers;

import com.example.demo.models.DungLuongPin;
import com.example.demo.services.DungLuongPinService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/dung-luong-pin")
@Controller
public class DungLuongPinController {

    @Autowired
    private DungLuongPinService dungLuongPinService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin,
                          @RequestParam("pageNum") Optional<Integer> pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize) {

        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        Page<DungLuongPin> page = dungLuongPinService.getAll(pageable);
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin.jsp");
        model.addAttribute("listDungLuongPin", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "home/layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin,
                                @RequestParam("pageNum") Optional<Integer> pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize) {

        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        Page<DungLuongPin> page = dungLuongPinService.getAll1(pageable);
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin-delete.jsp");
        model.addAttribute("listDungLuongPin", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "home/layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        model.addAttribute("dungLuongPin", new DungLuongPin());
        model.addAttribute("contentPage", "../dungluongpin/add.jsp");
        return "home/layout";
    }

    @PostMapping("/add")
    public String addDungLuongPin(Model model, @ModelAttribute("dungLuongPin") @Valid DungLuongPin dungLuongPin, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../dungluongpin/add.jsp");
            return "home/layout";
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String ma = "DLP" + (dungLuongPinService.findAll().size() + 1);
        dungLuongPin.setMa(ma);
        dungLuongPin.setNgayTao(date);
        dungLuongPin.setTinhTrang(0);

        dungLuongPinService.add(dungLuongPin);
        return "redirect:/dung-luong-pin/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String updateDungLuongPin(Model model, @PathVariable("id") UUID id, @ModelAttribute("dungLuongPin") @Valid DungLuongPin dungLuongPin, BindingResult bindingResult, @RequestParam("pageNum") Optional<Integer> pageNum,
                                     @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize) {
        if (bindingResult.hasErrors()) {
            return "../dungluongpin/dung-luong-pin-update";
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        dungLuongPin.setNgayCapNhat(date);
        dungLuongPinService.update(id, dungLuongPin);
        return "redirect:/dung-luong-pin/hien-thi";
    }

    @GetMapping("/update-tt")
    public String updateTT(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        dungLuongPin.setNgayCapNhat(date);

        dungLuongPinService.updateTT();
        System.out.println("cc: ");
        Page<DungLuongPin> page = dungLuongPinService.getAll1(pageable);
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin-delete.jsp");
        model.addAttribute("listDungLuongPin", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "home/layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);

        DungLuongPin dungLuongPin1 = dungLuongPinService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        dungLuongPin1.setNgayCapNhat(date);
        dungLuongPin1.setTinhTrang(1);
        dungLuongPinService.update(id, dungLuongPin1);
        Page<DungLuongPin> page = dungLuongPinService.getAll(pageable);
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin.jsp");
        model.addAttribute("listDungLuongPin", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "home/layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        DungLuongPin dungLuongPin1 = dungLuongPinService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        dungLuongPin1.setNgayCapNhat(date);

        dungLuongPin1.setTinhTrang(0);
        dungLuongPinService.update(id, dungLuongPin1);
        Page<DungLuongPin> page = dungLuongPinService.getAll1(pageable);
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin-delete.jsp");
        model.addAttribute("listDungLuongPin", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "home/layout";
    }


    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "15") Integer pageSize) {
        DungLuongPin sp = dungLuongPinService.findById(id);
        model.addAttribute("dungLuongPin", sp);
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        Page<DungLuongPin> page = dungLuongPinService.getAll(pageable);
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin-update.jsp");
        model.addAttribute("listDungLuongPin", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "home/layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @RequestParam("search") String sreach, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        List<DungLuongPin> listDungLuongPin = dungLuongPinService.search0(sreach);
        model.addAttribute("listDungLuongPin", listDungLuongPin);
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin.jsp");
        return "home/layout";
    }

    @PostMapping("/search-1")
    public String search1(Model model, @RequestParam("search") String sreach, @ModelAttribute("dungLuongPin") DungLuongPin dungLuongPin) {
        List<DungLuongPin> listDungLuongPin = dungLuongPinService.search1(sreach);
        model.addAttribute("listDungLuongPin", listDungLuongPin);
        model.addAttribute("contentPage", "../dungluongpin/dung-luong-pin-delete.jsp");
        return "home/layout";
    }
}
