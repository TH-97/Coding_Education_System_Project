package com.project.geomin.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler{
	// Json객체 → Java객체
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	//유저리스트 <접속세션, 유저이름>
	private static final ConcurrentHashMap<String, WebSocketSession> CLIENT = new ConcurrentHashMap<>();
	
	//채팅방 목록
	private static final HashMap<String, HashMap<String, WebSocketSession>> roomList = new HashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println();

		CLIENT.put(session.getId(), session);
		//System.out.println(session.getId());
		System.out.println(roomList);
		String roomNumber = session.getUri().toString().split("/")[5];
		//System.out.println(roomNumber);
		//int idx = 0;
		boolean flag = false;
		for(String key : roomList.keySet()) {
			if(key.equals(roomNumber)) {
				flag = true;
				break;
			}
		}
		
		if(flag) {
			HashMap<String, WebSocketSession> map = roomList.get(roomNumber);
			map.put(session.getId(), session);
		}else {
			HashMap<String, WebSocketSession> map = new HashMap<>();
			map.put(session.getId(), session);
			roomList.put(roomNumber, map);
		}
		
		
		//HashMap<String, WebSocketSession> map = new HashMap();
		//map.put("roomNumber", session);
		
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("나가거든 얘기좀");
		String roomNumber = session.getUri().toString().split("/")[5];
		for(Entry<String, HashMap<String, WebSocketSession>> Roomkey : roomList.entrySet()) {
			if(Roomkey.getKey().equals(roomNumber)) {
				Roomkey.getValue().remove(session.getId());
				
			}
		}
		
	
	}



	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(session.getId());
		System.out.println(message.getPayload());
		String roomNumber = session.getUri().toString().split("/")[5];
		
		String userid = session.getId();
		for(Entry<String, HashMap<String, WebSocketSession>> Roomkey : roomList.entrySet()) {
				if(Roomkey.getKey().equals(roomNumber)) {
					for(Entry<String, WebSocketSession>  userKey : Roomkey.getValue().entrySet()) {
						userKey.getValue().sendMessage(message);
					}
					break;
		}
			
			//key.getValue().sendMessage(message);
			
			//if(!key.getKey().equals(userid)) {
				
			//}
			
			System.out.println("뿡");
		}
	}





	

}
