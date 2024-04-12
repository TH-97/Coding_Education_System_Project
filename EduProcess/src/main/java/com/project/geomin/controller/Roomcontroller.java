package com.project.geomin.controller;

import com.project.geomin.chat.service.ChatService;
import com.project.geomin.command.GroupVO;
import com.project.geomin.command.JoinChatVO;
import com.project.geomin.command.JoinGroupVO;
import com.project.geomin.command.RoomVO;
import com.project.geomin.handler.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	public String moveChatting(RoomVO vo , Model model) {

		model.addAttribute("enterRoom", vo);

		return "chat/chat";
	}

	@PostMapping("/createRoomDB")
	public String createRoomDB(Principal principal, JoinChatVO vo){
		String userId = principal.getName();
		System.out.println(vo);
		String roomName = vo.getRc_title();
		System.out.println(roomName);

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

}
