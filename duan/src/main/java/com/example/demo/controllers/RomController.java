package com.example.demo.controllers;

import com.example.demo.models.Rom;
import com.example.demo.services.RomService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
//@RequestMapping("/rom")
public class RomController {
    @Autowired
    RomService romService;

    @GetMapping("/rom/hien-thi")
    public String hienthi(@ModelAttribute("dulieuxem") Rom dulieuxem, Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "15", required = false) Integer size) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<Rom> list = romService.getAll0(pageable);
        model.addAttribute("contentPage", "../rom/rom.jsp");
        model.addAttribute("hsp", list.getContent());
        model.addAttribute("total", list.getTotalPages());

        return "home/layout";
    }

    @GetMapping("/rom/view-add")
    public String viewAdd(@ModelAttribute("dulieuxem") Rom dulieuxem, Model model) {
        model.addAttribute("dulieuxem", new Rom());
        model.addAttribute("contentPage", "../rom/add.jsp");

        return "home/layout";
    }

    @GetMapping("/rom/hien-thi-tung-xoa")
    public String hienthixoa(@ModelAttribute("dulieuxem") Rom dulieuxem, Model model, @RequestParam("num") Optional<Integer> num,
                             @RequestParam(name = "size", defaultValue = "15", required = false) Integer size) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<Rom> list = romService.getall1(pageable);
        model.addAttribute("contentPage", "../rom/rom-tung-xoa.jsp");
        model.addAttribute("hsp", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "home/layout";
    }

    @GetMapping("/rom/detail/{id}")
    public String viewupdate(@PathVariable("id") UUID id, @ModelAttribute("dulieuxem") Rom dulieuxem, Model model, @RequestParam("num") Optional<Integer> num,
                             @RequestParam(name = "size", defaultValue = "15", required = false) Integer size) {
        Rom hsp = romService.findById(id);
        model.addAttribute("dulieuxem", hsp);
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<Rom> list = romService.getAll0(pageable);
        model.addAttribute("contentPage", "../rom/rom-update.jsp");
        model.addAttribute("hsp", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "home/layout";
    }

    @PostMapping("rom/add")
    public String add(Model model, @ModelAttribute("dulieuxem") @Valid Rom dulieuxem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../rom/add.jsp");
            return "home/layout";
        }

        dulieuxem.setNgayTao(Date.valueOf(LocalDate.now()));
        dulieuxem.setMa("R" + String.valueOf(romService.findAll().size() + 1));
        romService.add(dulieuxem);
        return "redirect:/rom/hien-thi";
        // Tiếp tục xử lý và trả về view tương ứng
    }

    @PostMapping("rom/update/{id}")
    public String update(@ModelAttribute("dulieuxem") @Valid Rom dulieuxem, BindingResult bindingResult, @PathVariable("id") UUID id, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "../rom/rom-update.jsp");
            return "home/layout";
        }

        Rom hsp = romService.findById(id);
        Date ngayTao = hsp.getNgayTao();
        hsp.setDungLuong(dulieuxem.getDungLuong());
        hsp.setNgayTao(ngayTao);
        // Gán ngày hiện tại
        hsp.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hsp.setTinhTrang(dulieuxem.getTinhTrang());
        hsp.setMoTa(dulieuxem.getMoTa());
        romService.update(id, hsp);
        return "redirect:/rom/hien-thi";
    }

    @PostMapping("/rom/search")
    public String search(@RequestParam("search") String search, @ModelAttribute("dulieuxem") Rom dulieuxem, Model model, @RequestParam("num") Optional<Integer> num,
                         @RequestParam(name = "size", defaultValue = "15", required = false) Integer size) {
        List<Rom> list = romService.search(search);


        model.addAttribute("contentPage", "../rom/rom.jsp");
        model.addAttribute("hsp", list);
//        model.addAttribute("total", list);
        return "home/layout";
    }

    @PostMapping("/rom/search2")
    public String search2(@RequestParam("search") String search, @ModelAttribute("dulieuxem") Rom dulieuxem, Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "15", required = false) Integer size) {
        List<Rom> list = romService.search2(search);


        model.addAttribute("contentPage", "../rom/rom-tung-xoa.jsp");
        model.addAttribute("hsp", list);
//        model.addAttribute("total", list);
        return "home/layout";
    }

    @GetMapping("rom/delete/{id}")
    public String delete(Model model, @ModelAttribute("dulieuxem") Rom dulieuxem, @PathVariable("id") UUID id, @RequestParam("num") Optional<Integer> num,
                         @RequestParam(name = "size", defaultValue = "15", required = false) Integer size) {
        Rom rom = romService.findById(id);

        rom.setTinhTrang(1);
        romService.add(rom);
        Pageable pageable = PageRequest.of(num.orElse(0), size);
        Page<Rom> list = romService.getAll0(pageable);
        model.addAttribute("contentPage", "../rom/rom.jsp");
        model.addAttribute("hsp", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "home/layout";

    }

    @GetMapping("/rom/khoi-phuc/{id}")
    public String khoiphuc(
            Model model,
            @PathVariable("id") UUID id,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "15", required = false) Integer size


    ) {

        Rom rom1 = romService.findById(id);
        rom1.setTinhTrang(0);
        romService.add(rom1);
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<Rom> list = romService.getall1(pageable);

        model.addAttribute("contentPage", "../rom/rom-tung-xoa.jsp");
        model.addAttribute("hsp", list.getContent());
        model.addAttribute("total", list.getTotalPages());


        return "home/layout";
    }

    @GetMapping("/rom/khoi-phuc-het")
    public String khoiphuchet(
            Model model,
            @RequestParam("num") Optional<Integer> num,
            @RequestParam(name = "size", defaultValue = "15", required = false) Integer size


    ) {
        romService.update0();
        Sort sort = Sort.by("ma").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<Rom> list = romService.getall1(pageable);

        model.addAttribute("contentPage", "../rom/rom-tung-xoa.jsp");
        model.addAttribute("hsp", list.getContent());
        model.addAttribute("total", list.getTotalPages());


        return "home/layout";
    }
}
