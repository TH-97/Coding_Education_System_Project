package com.project.geomin.controller;

import com.project.domitory.command.PageVO;
import com.project.geomin.command.WorkVO;
import com.project.geomin.service.WorkService;
import com.project.geomin.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/work")
public class WorkController {

    @Autowired
    @Qualifier("workService")
    private WorkService workService;

    @GetMapping("/workeva")
    public String list(Model model, Criteria cri, HttpSession session) {
        String user_id = (String)session.getAttribute("user_id");
        //목록을 가지고 나와서 데이터를 담고 나감
        ArrayList<WorkVO> list = workService.getList(cri,user_id); //데이터
        int total=workService.getTotal(cri,user_id);
        PageVO pageVo = new PageVO(cri, total);
        model.addAttribute("list", list);
        model.addAttribute("pageVO", pageVo);

        return "work/workeva";
    }
    @GetMapping("/workdis")
    public String list2(Model model, Criteria cri, HttpSession session) {
        String user_id = (String)session.getAttribute("user_id");
        //목록을 가지고 나와서 데이터를 담고 나감
        ArrayList<WorkVO> list = workService.getList(cri,user_id); //데이터
        int total=workService.getTotal(cri,user_id);
        PageVO pageVo = new PageVO(cri, total);
        model.addAttribute("list", list);
        model.addAttribute("pageVO", pageVo);

        return "work/workdis";
    }

    @GetMapping("/makework")
    public String reg() {
        System.out.println("그럼 이건 타?");
        return "work/makework";
    }

    // 상품등록요청
    @PostMapping("/workForm")
    public String workForm(WorkVO vo,
                            HttpSession session) {


        int result = workService.regist(vo);

        return "redirect:/board/board";
    }
}
