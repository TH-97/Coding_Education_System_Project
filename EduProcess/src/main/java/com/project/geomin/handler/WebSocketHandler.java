package com.project.geomin.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.geomin.chat.service.ChatMapper;
import com.project.geomin.command.ChatMessageVO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler{
	// Json객체 → Java객체 매핑
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private ChatMapper chatMapper;

	//웹소켓에 접속한 유저들의 세션을 관리하기 위한 자료구조
	@Getter
	static final HashMap<String, HashMap<String, WebSocketSession>> ROOMMap = new HashMap<>();


	//웹소켓 연결시
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//웹소켓 입장

		//System.out.println(session.getId());
		System.out.println(ROOMMap);
		String roomNumber = session.getUri().toString().split("/")[5];
		//System.out.println(roomNumber);
		//int idx = 0;
		boolean flag = false;
		for(String key : ROOMMap.keySet()) {
			if(key.equals(roomNumber)) {
				flag = true;
				break;
			}
		}
		
		if(flag) {
			HashMap<String, WebSocketSession> map = ROOMMap.get(roomNumber);
			map.put(session.getId(), session);
		}else {
			HashMap<String, WebSocketSession> map = new HashMap<>();
			map.put(session.getId(), session);
			ROOMMap.put(roomNumber, map);
		}

		
	}


	//웹소켓 접속해제시
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("나가거든 얘기좀");
		String roomNumber = session.getUri().toString().split("/")[5];
		for(Entry<String, HashMap<String, WebSocketSession>> Roomkey : ROOMMap.entrySet()) {
			if(Roomkey.getKey().equals(roomNumber)) {
				Roomkey.getValue().remove(session.getId());
				
			}
		}
	}


	//웹소켓을 통해 메시지전송시
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(session.getId());
		System.out.println(message.getPayload());
		String roomNumber = session.getUri().toString().split("/")[5];
		System.out.println(roomNumber + "뿡");


		for(Entry<String, HashMap<String, WebSocketSession>> Roomkey : ROOMMap.entrySet()) {
				if(Roomkey.getKey().equals(roomNumber)) {
					for(Entry<String, WebSocketSession>  userKey : Roomkey.getValue().entrySet()) {
						userKey.getValue().sendMessage(message);
					}
					break;
				}
		}
		//1대1채팅만 채팅내역을 디비에 저장 후
		//
		ChatMessageVO vo = objectMapper.readValue(message.getPayload(), ChatMessageVO.class);
		System.out.println(vo);
		if(vo.getRc_usage().equals("one")){

		}
	}





	

}
