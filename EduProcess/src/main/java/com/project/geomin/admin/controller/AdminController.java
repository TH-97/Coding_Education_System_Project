package com.project.geomin.admin.controller;

import com.project.geomin.admin.service.AdminService;
import com.project.geomin.command.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/test")
    public String test(Model model){
        AdminVO vo = new AdminVO();
        AdminVO T = adminService.getT(vo);
        AdminVO F = adminService.getF(vo);
        List<AdminVO> list = new ArrayList<>();
        list.add(T);
        list.add(F);

        model.addAttribute("list",list);

        return "test";
    }
}
