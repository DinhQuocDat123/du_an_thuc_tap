package com.example.demo.controllers;

import com.example.demo.services.DataIntermediateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @Autowired
    DataIntermediateService dataIntermediateService;

    @RequestMapping("/home")
    private String showHome(Model model){
        model.addAttribute("nhanVien", dataIntermediateService.getSharedDataNhanVien());
        return  "home/layout";
//                "chip/basic-table";
    }

    @GetMapping("/homes")
    private String showHomes(Model model){
        return  "ban-hang-online/so-sanh-san-pham";
//                "chip/basic-table";
    }
}
