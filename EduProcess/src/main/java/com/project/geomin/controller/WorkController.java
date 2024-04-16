package com.project.geomin.controller;

import com.project.geomin.command.*;
import com.project.geomin.student.service.WorkService;
import com.project.geomin.user.security.MyUserDetails;
import com.project.geomin.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/work")
public class WorkController {

    @Autowired
    @Qualifier("workService")
    private WorkService workService;

    @GetMapping("/workdis")
    public String list(Model model, Criteria cri, Authentication authentication, GroupSearchVO searchVO) {
        MyUserDetails dd = (MyUserDetails)authentication.getPrincipal();
        ArrayList<WorkVO> list = workService.getList(cri, dd.getUsername());

        //그룹 가져오기
        GroupVO vo2 = new GroupVO();
        ArrayList<GroupVO> gList = workService.selectGroup(vo2, searchVO);
        model.addAttribute("gList", gList);
        int total=workService.getTotal(cri,dd.getUsername());
        PageeeVO pageVo = new PageeeVO(cri, total);
        model.addAttribute("list", list);
        model.addAttribute("pageVO", pageVo);
        System.out.println("---------------");
        System.out.println(gList.toString());
        System.out.println("----------------");

        //배포

        return "work/workdis";
    }
    @GetMapping("/workeva")
    public String list2(Model model, Criteria cri, HttpSession session) {
        String user_id = (String)session.getAttribute("user_id");
        ArrayList<WorkVO> list2 = workService.getList(cri,user_id); //데이터
        int total=workService.getTotal(cri,user_id);
        PageeeVO pageVo = new PageeeVO(cri, total);
    @GetMapping("/makework")
    public String reg() {
        return "work/makework";
    }

    @PostMapping("/workForm")
    public String workForm(WorkVO vo,
                            HttpSession session) {


        int result = workService.regist(vo);

        return "redirect:/work/workdis";
    }
    @GetMapping("/worksub")
    public String detail(@RequestParam("h_no") int user_id,
                         Model model
                         ) {
        WorkVO vo = workService.getDetail(user_id); //게시글 내용

        model.addAttribute("vo", vo);


        return "/work/worksub";
    }
    @PostMapping("/subForm")
    public String subForm(@RequestBody Map<String, Object> map){
        System.out.println("ffffff");
        System.out.println(map.toString());
        List<Map<String,String>> list=(List<Map<String, String>>) map.get("selectedValues");
        for(Map<String,String> map2 :list){
            String h_no = map2.get("workNo");
            String sg_no = map2.get("groupNo");
            int n = workService.insertHw(h_no,sg_no);
        }
        return "redirect:/work/workdis";
    }

}
