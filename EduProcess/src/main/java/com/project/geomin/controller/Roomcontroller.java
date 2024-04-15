package com.project.geomin.controller;

import com.project.geomin.chat.service.ChatService;
import com.project.geomin.command.*;
import com.project.geomin.handler.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class Roomcontroller {

	@Autowired
	@Qualifier("chatService")
	private ChatService chatService;
	
	@GetMapping("/room")
	public String room(Principal principal, Model model) {
		String userId = principal.getName();
		ArrayList<RoomVO> roomList = chatService.selectmyRoom(userId);
		model.addAttribute("roomList", roomList);
		System.out.println(roomList);
		return "chat/room";
	}

	@GetMapping("/moveChatting")
	public String moveChatting(Principal principal,RoomVO vo , Model model) {
		model.addAttribute("enterRoom", vo);
		model.addAttribute("chatList", chatService.loadChatting(vo));
		chatService.disActivateChatStatus(principal.getName(), vo);
		return "chat/chat";
	}

	@PostMapping("/createRoomDB")
	public String createRoomDB(Principal principal, JoinChatVO vo, RedirectAttributes ra){
		String userId = principal.getName();
		System.out.println(vo);
		String roomName = vo.getRc_title();
		System.out.println(roomName);


		if(chatService.isAlreadyCreate(userId ,vo.getUser_id()) != 0){
			ra.addFlashAttribute("msg", "이미 존재하는 채팅방 입니다.");
			return "redirect:/room";
		}


		if(roomName != null && !roomName.trim().equals("") ) {
			//DB방만들기
			vo.setRc_no(UUID.randomUUID().toString());
			chatService.CreateChatRoom(vo);
			//1대1방 신청
			chatService.oneToOneJoinChat(vo, userId);
		}
		return "redirect:/room";

	}


	//////////////////////////Rest영역
	@PostMapping("/createRoom")
	@ResponseBody
	public void createRoom(@RequestBody RoomVO vo, Principal principal){
		String userId = principal.getName();
		System.out.println(vo);
		String roomName = vo.getRc_title();
		System.out.println(roomName);

		if(roomName != null && !roomName.trim().equals("") ) {
			//DB방만들기
			JoinChatVO room = new JoinChatVO();
			room.setRc_no(UUID.randomUUID().toString());
			room.setRc_title(roomName);
			chatService.CreateChatRoom(room);
			chatService.oneToOneJoinChat(room, userId);
		}



	}


	@ResponseBody
	@GetMapping("/chat/getMyTeacher")
	public List<GroupVO> getMyTeacher(Principal principal) {
		String userId = principal.getName();
		ArrayList<GroupVO> myTeacherList = chatService.getMyTeacher(userId);
		return myTeacherList;
	}
	@ResponseBody
	@GetMapping("/chat/getMyGroup")
	public List<GroupVO> getMyGroup(Principal principal) {
		String userId = principal.getName();
		ArrayList<GroupVO> MyGroupList = chatService.getMyGroup(userId);
		return MyGroupList;
	}

	@ResponseBody
	@GetMapping("/chat/getMyStudent")
	public ResponseEntity<ArrayList<JoinGroupVO>> myStudent(GroupVO vo) {
		if(vo.getSg_no() == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ArrayList<JoinGroupVO> myStudentList = chatService.getMyStudent(vo);
		System.out.println(myStudentList);

		return new ResponseEntity<>(myStudentList, HttpStatus.OK);
	}

}
