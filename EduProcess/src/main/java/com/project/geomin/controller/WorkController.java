package com.project.geomin.controller;

import com.project.geomin.command.GroupSearchVO;
import com.project.geomin.command.GroupVO;
import com.project.geomin.command.PageeeVO;
import com.project.geomin.command.WorkVO;
import com.project.geomin.student.service.WorkService;
import com.project.geomin.user.security.MyUserDetails;
import com.project.geomin.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
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
    public String subForm(WorkVO vo, RedirectAttributes ra){
        System.out.println(1);
        System.out.println(vo);
        System.out.println(1);
        ra.addAttribute("sg_no", vo.getSg_no());
        return "redirect:/work/workdis";
    }
}
