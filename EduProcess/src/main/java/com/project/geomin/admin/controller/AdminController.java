package com.project.geomin.admin.controller;

import com.project.geomin.admin.aws.s3.S3Service;
import com.project.geomin.admin.service.AdminService;
import com.project.geomin.command.AdminVO;
import com.project.geomin.command.ReviewVO;
import com.project.geomin.review.service.ReviewService;
import com.project.geomin.user.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
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
    S3Service s3Service;
    @Autowired
    @Qualifier("AdminService")
    AdminService adminService;
    @Autowired
    @Qualifier("ReviewService")
    ReviewService reviewService;

    @GetMapping("/test")
    public String test(){

        return "test";
    }
    @GetMapping("/conMa")
    public String conMa(Model model){
        List<AdminVO> list = adminService.getContent();

        model.addAttribute("list",list);
        return "user/content";
    }
    @PostMapping("/content")
    public String content(Model model, @RequestParam("content_name")String con_nm, Authentication auth) {

        AdminVO T = adminService.getT(con_nm);

        List<AdminVO> F = adminService.getF(con_nm);

        List<ReviewVO> reviewList = reviewService.getReview(con_nm);

        String what ="";
        if (auth != null){
            MyUserDetails myuser = (MyUserDetails) auth.getPrincipal();
            what = myuser.getUsername();
        } else {
            what = null;
        }

        model.addAttribute("review",reviewList);

        System.out.println(F);
        model.addAttribute("content",T);
        model.addAttribute("content_list", F);
        model.addAttribute("myuser", what);
        return "user/user_content";
    }
    @PostMapping("/reviewSave")
    public String reviewSace(Model model,@RequestParam("content_name")String con_nm,
                             @RequestParam("textarea")String review_context,
                             @RequestParam("reviewStar")int star,
                             @RequestParam("user_id")String user_id,
                             Authentication auth){
        //리뷰 저장
        reviewService.inputReview(con_nm,star,review_context,user_id);
        //모든 리뷰 가져오기
        List<ReviewVO> reviewList = reviewService.getReview(con_nm);
        System.out.println(reviewList);
        //컨텐츠 가져오기
        AdminVO T = adminService.getT(con_nm);
        List<AdminVO> F = adminService.getF(con_nm);

        String what ="";
        if (auth != null){
            MyUserDetails myuser = (MyUserDetails) auth.getPrincipal();
            what = myuser.getUsername();
        } else {
            what = null;
        }

        model.addAttribute("review",reviewList);
        model.addAttribute("content",T);
        model.addAttribute("content_list", F);

        model.addAttribute("myuser", what);

        return "user/user_content";

    }
    @GetMapping("/video")
    public String video(Model model,@RequestParam("src") String src){
        model.addAttribute("src",src);
        return "user/video";
    }
    @PostMapping("/delete_content")
    public String delete_content(Model model,@RequestParam(value = "con_nm", required= false) String con_nm){
        s3Service.delete(con_nm);
        adminService.deleteContent(con_nm);

        List<AdminVO> list = adminService.getContent();

        model.addAttribute("list",list);
        return "user/content";
    }
    @GetMapping("/FAQ")
    public String FAQ(){
        System.out.println("들어옴");

        return "admin/FAQ";
    }
    @GetMapping("/Q&A")
    public String QA(){
        System.out.println("들어옴");
        return "admin/Q&A";
    }


}
