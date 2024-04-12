package com.project.geomin.admin.controller;

import com.project.geomin.admin.service.AdminService;
import com.project.geomin.command.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;


    @GetMapping("/conMa")
    public String conMa(Model model){
        List<AdminVO> list = adminService.getContent();

        model.addAttribute("list",list);
        System.out.println(list);
        return "user/content";
    }
    @PostMapping("/content")
    public String content(Model model,@RequestParam("content_name")String content_name){
        System.out.println(content_name);
        AdminVO T = adminService.getT(content_name);
        System.out.println(T);
        List<AdminVO> F = adminService.getF(content_name);
        System.out.println(F);
        model.addAttribute("content",T);
        model.addAttribute("content_list", F);
        return "user/user_content";
    }
    @GetMapping("/video")
    public String video(Model model,@RequestParam("src") String src){
        model.addAttribute("src",src);
        return "user/video";
    }
    @PostMapping("/delete_content")
    public String delete_content(@RequestParam(value = "con_nm", required= false) String con_nm){
        System.out.println(con_nm);
        return "user/content";
    }

}
