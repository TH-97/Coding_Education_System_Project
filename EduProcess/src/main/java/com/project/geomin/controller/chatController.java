package com.project.geomin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;




@Controller
@RequiredArgsConstructor
public class chatController {
	
	private final SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/chat/send")
	public void chat(@Payload Map<String,Object> data, @DestinationVariable("roomid") String roomid) {
		System.out.println(data);
		simpMessagingTemplate.convertAndSend("/topic/"+roomid, data);
		
	}
	
	 @GetMapping("/chat/{roomId}")
	 public String chatGET(@PathVariable("roomId") String id ){
	    return "chat";
	 }

}
