package com.project.geomin.controller;

import com.project.geomin.command.RoomVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class Roomcontroller {
	//임시방편 채팅방
	List<RoomVO> roomList = new ArrayList<>();
	static int roomNumber = 0;

	
	@GetMapping("/room")
	public String room() {
		return "chat/room";
	}

	@GetMapping("/moveChatting")
	public String moveChatting(RoomVO vo , Model model) {
		String roomNumber = vo.getRoomNumber();
		System.out.println(vo);

		List<RoomVO> myRoom = roomList.stream().filter(v -> v.getRoomNumber().equals(roomNumber)).collect(Collectors.toList());
		if(myRoom.size() <= 0) {
			return "chat/room";
		}
		System.out.println(myRoom);
		model.addAttribute("enterRoom", myRoom);

		return "chat/chat";
	}


	//Rest영역
	@PostMapping("/createRoom")
	@ResponseBody
	public List<RoomVO> createRoom(@RequestBody RoomVO vo){
		System.out.println(vo);
		String roomName = vo.getRoomName();
		System.out.println(roomName);
		
		if(roomName != null && !roomName.trim().equals("") ) {
			RoomVO room = new RoomVO();
			room.setRoomNumber(UUID.randomUUID().toString());
			room.setRoomName(roomName);
			roomList.add(room);
		}
		
		
		return roomList;
	}
	
	@GetMapping("/getRoom")
	@ResponseBody
	public List<RoomVO> getRoom(){
		return roomList;
	}
	



}
