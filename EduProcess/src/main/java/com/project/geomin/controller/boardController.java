package com.project.geomin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.geomin.board.service.Criteria;
import com.project.geomin.board.service.boardService;
import com.project.geomin.command.BoardPageVO;
import com.project.geomin.command.QuestionVO;
import com.project.geomin.command.RegistEduVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/board")
public class boardController {

	@Autowired
	@Qualifier("boardService")
	private boardService boardService;
	
	@GetMapping("/list")
	public String list(Model model,Criteria cri) {
	
		 
		BoardPageVO vo = new BoardPageVO(cri, 10);
		ArrayList<QuestionVO> questionList = boardService.questionList(cri);
		model.addAttribute("questionList",questionList);
		model.addAttribute("cri",cri);
		model.addAttribute("BoardPageVO",vo);
		return"board/list";
	}
	
	@GetMapping("/faq_list")
	public String faq_list() {
		return "board/faq_list";
	}
	@GetMapping("/regist_p")
	public String regist_p() {
		return "board/regist_p";
	}
	@GetMapping("/regiPopup")
	public String regiPopup() {
		return "board/regiPopup";
	}
	
	@PostMapping("/regist_educator")
	public String regist_educator(@RequestParam("edu_name") String edu_name, @RequestParam("edu_sub") String edu_sub) {
		System.out.println("edu_name : " + edu_name);
		System.out.println("edu_sub : " + edu_sub);
		return "board/regist_p";
	}
	
	@PostMapping("/regist_educator1")
	public @ResponseBody String  regist_educator1(@RequestBody List<RegistEduVO> vo) {
		for (RegistEduVO experience : vo) {
            System.out.println("Company: " + experience.getCompany());
            System.out.println("Position: " + experience.getPosition());
            System.out.println("Duration: " + experience.getDuration());
        }
		return vo.toString();
	}
	
	@GetMapping("/regiContents")
	public String regiContents() {
		return "board/regiContents";
	}
	
	@PostMapping("/regist")
	public String regist(QuestionVO vo) {
		int a =boardService.boardRegi(vo);
		if(a ==1) {
			return "redirect:/board/list";
		}else {
			//실행오류로 
			return "";
			
		}
	}
	


}
