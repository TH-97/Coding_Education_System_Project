package com.project.geomin.chat.service;

import com.project.geomin.command.GroupVO;
import com.project.geomin.command.JoinChatVO;
import com.project.geomin.command.RoomVO;
import com.project.geomin.handler.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("chatService")
public class ChatServiceImpl implements  ChatService{
    @Autowired
    private ChatMapper chatMapper;




    @Override
    public int CreateChatRoom(JoinChatVO vo) {
        return chatMapper.CreateChatRoom(vo);

    }

    @Override
    public ArrayList<RoomVO> selectmyRoom(String userId) {
        return chatMapper.selectmyRoom(userId);
    }

    @Override
    public ArrayList<GroupVO> getMyTeacher(String userId) {
        return chatMapper.getMyTeacher(userId);
    }

    @Override
    public void oneToOneJoinChat(JoinChatVO vo, String myUserId) {
       if(vo.getUser_id() != null){
           vo.setJc_status("비활성화");
           chatMapper.oneToOneJoinChat(vo);
       }
       vo.setUser_id(myUserId);
        vo.setJc_status("활성화");
       chatMapper.oneToOneJoinChat(vo);
    }
}
