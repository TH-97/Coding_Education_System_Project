package com.project.geomin.controller;

import com.project.geomin.chat.service.ChatService;
import com.project.geomin.command.*;
import com.project.geomin.edu.service.Criteria;
import com.project.geomin.edu.service.EduService;
import com.project.geomin.user.security.MyUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;

@RequestMapping("/edu")
@Controller
public class EduController {

	private static final Logger log = LoggerFactory.getLogger(EduController.class);
	@Autowired
	@Qualifier("eduservice")
	private EduService eduService;

	@Autowired
	@Qualifier("chatService")
	private ChatService chatService;

	//교육자 그룹 등록
	@GetMapping("/groupRegist")
	public String groupRegist() {
		return "edu/groupRegist";
	}
	@PostMapping("/groupRegistDB")
	public String groupRegistDB(GroupVO vo, Principal principal){
		//그룹방 생성
		eduService.groupRegist(vo);
		int sgNo = eduService.getSgNo();

		//채팅방도 생성
		JoinChatVO joinChatVO = new JoinChatVO();
		joinChatVO.setRc_title(vo.getSg_name()+"의 채팅방");
		joinChatVO.setRc_no(UUID.randomUUID().toString());
		System.out.println(joinChatVO);
		chatService.groupChatCreate(joinChatVO);



		//일단 선생님 채팅방에 등록
		joinChatVO.setSg_no(sgNo);
		joinChatVO.setUser_id(principal.getName());
		joinChatVO.setJc_status("활성화");
		chatService.groupChatJoin(joinChatVO);
		return "redirect:/edu/groupSelect";
	}
	@GetMapping("/groupUpdate")
	public String groupUpdate(GroupVO vo, Model model){
		System.out.println(vo);
		ArrayList<GroupVO> groupList = eduService.selectGroupPK(vo);
		model.addAttribute("groupList", groupList.get(0));
		return "edu/groupUpdate";
	}

	@PostMapping("/groupUpdateReal")
	public String groupUpdateReal(GroupVO vo){
		System.out.println(vo);
		eduService.groupUpdateReal(vo);
		return "redirect:/edu/groupSelect";
	}


	//교육자 그룹 조회
	@GetMapping("/groupSelect")
	public String groupSelect(Principal principal, Model model, GroupSearchVO searchVO){
		//로그인유저 정보 가져오기
		//Authentication authentication;
		//MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal()
		//System.out.println(principal.getName());

		GroupVO vo = new GroupVO();
		vo.setUser_id(principal.getName());

		ArrayList<GroupVO> groupList= eduService.selectGroup(vo, searchVO);
		model.addAttribute("groupList", groupList);

		if(searchVO.getUser_nm() != null && !searchVO.getUser_nm().isEmpty() ){
			JoinGroupVO jvo = new JoinGroupVO();
			jvo.setUser_nm(searchVO.getUser_nm());
			jvo.setSg_user_id(principal.getName());
			ArrayList<JoinGroupVO> groupStudentList = eduService.selectStudent(jvo);

			model.addAttribute("groupStudentList", groupStudentList);

		}

		return "edu/groupSelect";
	}


	@GetMapping("/groupJoin")
	public String groupJoin(Principal principal, Model model, Criteria cri, GroupSearchVO searchVO){
		String user_id = principal.getName();
		System.out.println(searchVO);
		if(cri.getCurrentPage() == null || cri.getShowPage() == null){
			cri = new Criteria();
		}
		System.out.println(cri);
		PageVO pageVO = new PageVO(cri, eduService.selectPageTotal(user_id, searchVO));
		ArrayList<JoinGroupVO> joinGroupList = eduService.myJoinGroup(cri, user_id, searchVO);

		model.addAttribute("pageVO", pageVO);
		model.addAttribute("joinGroupList", joinGroupList);
		return "edu/groupJoin";
	}



	@GetMapping("/groupContents")
	public String groupContents(){
		return "edu/groupContents";
	}

	//////////////////////////////////////////////////////////////////////////////////
	//REST 구역

	//그룹의 구성원 조회
	@ResponseBody
	@GetMapping("/groupStudSelect")
	public Map<String, Object> groupStudSelect(JoinGroupVO vo){


		ArrayList<JoinGroupVO> groupStudentList = eduService.selectStudent(vo);
		Map<String, Object> map = new HashMap<>();
		map.put("groupStudentList", groupStudentList);

		return map;
	}

	@ResponseBody
	@PostMapping("/applierApply")
	public String applierApply(@RequestBody Map<String, Object> map){
		System.out.println(map);
		//전체 승인로직
		eduService.applierApply(map);
		List<String> list = (List<String>)map.get("updateList");
		String userId = list.get(0);
		Integer sgNo = Integer.parseInt(list.get(1));

		JoinChatVO joinChatVO = new JoinChatVO();
		joinChatVO.setSg_no(sgNo);
		joinChatVO.setUser_id(userId);
		//채팅방번호 조회
		String rcNo = chatService.getRcNo(joinChatVO);

		//그룹방 가입
		joinChatVO.setRc_no(rcNo);
		joinChatVO.setJc_status("활성화");

		return "OK";
	}


	@ResponseBody
	@PostMapping("/groupDelete")
	public String groupDelete(@RequestBody Map<String, Object> map){
		System.out.println(map);
		String sgNo = (String)map.get("sg_no");
		eduService.joinGroupDelete(sgNo);
		eduService.groupDelete(sgNo);
		return "OK";
	}

	//학습자 상세조회
	@ResponseBody
	@GetMapping("/studentSelect")
	public UserVO studentSelect(UserVO vo){
		System.out.println(vo);
		return eduService.studentSelect(vo).get(0);
	}

	//학습자 그룹방출
	@ResponseBody
	@PostMapping("/deleteJoinStud")
	public String deleteJoinStud(@RequestBody JoinGroupVO vo){
		System.out.println(vo);
		eduService.deleteJoinStud(vo);
		return "OK";
	}


}
