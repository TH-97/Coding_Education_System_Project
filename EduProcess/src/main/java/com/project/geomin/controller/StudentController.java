package com.project.geomin.controller;

import com.project.geomin.command.GroupSearchVO;
import com.project.geomin.command.GroupVO;
import com.project.geomin.command.JoinGroupVO;
import com.project.geomin.command.PageVO;
import com.project.geomin.edu.service.Criteria;
import com.project.geomin.edu.service.EduService;
import com.project.geomin.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;

@RequestMapping("/student")
@Controller
public class StudentController {
    @Autowired
    @Qualifier("StudentService")
    private StudentService studentService;


    @GetMapping("/apply")
    public String apply(Principal principal, GroupSearchVO searchVO, Criteria cri, Model model) {
        String userId = principal.getName();

        if(cri.getCurrentPage() == null || cri.getShowPage() == null){
            cri = new Criteria();
        }
        //검색 and 페이징 처리
        PageVO pageVO = new PageVO(cri, studentService.selectGroupPageTotal(searchVO));
        ArrayList<GroupVO> groupList = studentService.allGroupSelect(cri, searchVO);
        model.addAttribute("groupList", groupList);
        model.addAttribute("pageVO", pageVO);

        ArrayList<JoinGroupVO> myApplyList = studentService.myApplyGroup(userId);
        model.addAttribute("myApplyList", myApplyList);
        return "student/apply";
    }

    @GetMapping("/groupApply")
    public String groupApply(JoinGroupVO vo, RedirectAttributes ra){
        System.out.println(vo);

        if(studentService.groupAplyCheck(vo) != 0){
            ra.addFlashAttribute("msg", "중복신청 불가합니다.");
        }else if(studentService.groupMaxAplyCheck(vo.getUser_id()) >= 7){
            ra.addFlashAttribute("msg", "그룹가입은 최대 7개 까지만 가입가능합니다.");
        }
        else{
            studentService.groupApply(vo);
            ra.addFlashAttribute("msg", "신청 완료.");
        }
        //신청최대 10개만
        return "redirect:/student/apply";
    }

    @GetMapping("/myGroup")
    public String select(Principal principal, Model model) {
        String userId = principal.getName();

        ArrayList<JoinGroupVO> myJoinGroup = studentService.myJoinGroup(userId);

        model.addAttribute("myJoinGroup", myJoinGroup);
        return "student/myGroup";
    }

    @GetMapping("/myGroupDetail")
    public String myGroupDetail() {

        return "student/myGroupDetail";
    }



    @ResponseBody
    @PostMapping("/deleteAply")
    public void deleteAply(@RequestBody Map<String, Object> map){
        System.out.println(map);
        JoinGroupVO vo = new JoinGroupVO();
        vo.setUser_id((String)map.get("userId"));
        vo.setSg_no(Integer.parseInt((String) map.get("sgNo")));
        studentService.deleteAply(vo);

    }
}
