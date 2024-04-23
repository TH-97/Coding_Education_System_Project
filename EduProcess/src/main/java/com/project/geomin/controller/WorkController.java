package com.project.geomin.controller;

import com.project.geomin.command.*;
import com.project.geomin.student.service.WorkService;
import com.project.geomin.user.security.MyUserDetails;
import com.project.geomin.util.Criteria;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        vo2.setUser_id(dd.getUsername());
        ArrayList<GroupVO> gList = workService.selectGroup(vo2, searchVO);
        System.out.println(gList.toString());
        UserVO vo3 = new UserVO();
        UserVO voo = dd.getUserVO();
        model.addAttribute("gList", gList);
        int total=workService.getTotal(cri,dd.getUsername());
        PageeeVO pageVo = new PageeeVO(cri, total);
        model.addAttribute("list", list);
        model.addAttribute("pageVO", pageVo);
        //배포
        return "work/workdis";
    }
    @GetMapping("/workdel")
    public String list3(Model model, Criteria cri, Authentication authentication) {
        MyUserDetails dd = (MyUserDetails)authentication.getPrincipal();
        ArrayList<WorkVO> list = workService.getList3(cri, dd.getUsername());
        //그룹 가져오기
        int total=workService.getTotal(cri,dd.getUsername());
        PageeeVO pageVo = new PageeeVO(cri, total);
        model.addAttribute("list", list);
        model.addAttribute("pageVO", pageVo);

        return "work/workdel";
    }
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
        System.out.println(map.toString());
        List<Map<String,String>> list = (List<Map<String, String>>) map.get("selectedValues");
        for(Map<String,String> map2 :list){
            String h_no = map2.get("workNo");
            String sg_no = map2.get("groupNo");
            workService.insertHw(h_no,sg_no);
        }
        return "redirect:/work/workcheck";
    }
    @GetMapping("/workcheck")
    public String list2(Model model, Criteria cri, Authentication authentication) {
        MyUserDetails dd = (MyUserDetails)authentication.getPrincipal();
        ArrayList<WorkVO> list = workService.getList2(cri, dd.getUsername());

        int total=workService.getTotal2(cri,dd.getUsername());

        PageeeVO pageVo = new PageeeVO(cri, total);
        model.addAttribute("list", list);
        model.addAttribute("pageVO", pageVo);
        System.out.println(list.toString());
        return "work/workcheck";
    }

    @GetMapping("/code")
    public String detail(@RequestParam("h_no") int h_no ,
                         Model model,
                         HttpSession session) {
        WorkVO vo = workService.getDetail(h_no);
        System.out.println(vo.getH_no());
        model.addAttribute("vo", vo);
        session.setAttribute("modelData",model);


        return "work/codeCompiler1";
    }
    @GetMapping("/workdell")
    public String deleteBoards(@RequestParam("h_no") int h_no) {
        workService.delete(h_no);
        return "redirect:workdis";
    }


}
