package com.project.geomin.chat.service;

import com.project.geomin.command.*;
import com.project.geomin.handler.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.chime.model.Room;

import java.util.ArrayList;
import java.util.List;

@Service("chatService")
public class ChatServiceImpl implements  ChatService{
    @Autowired
    private ChatMapper chatMapper;




    @Override
    public int CreateChatRoom(JoinChatVO vo) {
        return chatMapper.CreateChatRoom(vo);

    }

    @Override
    public int groupChatCreate(JoinChatVO vo) {
        return chatMapper.groupChatCreate(vo);
    }

    @Override
    public int groupChatJoin(JoinChatVO vo) {
        return chatMapper.groupChatJoin(vo);
    }

    @Override
    public void saveMessage(RoomVO vo) {
        ArrayList<ChatMessageVO> messagelist = WebSocketHandler.getMessageList().get(vo.getRc_no());
        if(messagelist != null && !messagelist.isEmpty()){
            chatMapper.saveMessage(messagelist);
            messagelist.clear();
        }
    }

    @Override
    public String getRcNo(JoinChatVO vo) {
        return chatMapper.getRcNo(vo);
    }

    @Override
    public void joinChatGroupDelete(JoinChatVO vo) {
        chatMapper.joinChatGroupDelete(vo);
    }

    @Override
    public void joinChatOneDelete(JoinChatVO vo) {
        chatMapper.joinChatOneDelete(vo);
    }

    @Override
    public void chatRoomDelete(JoinChatVO vo) {
        chatMapper.chatRoomDelete(vo);
    }

    @Override
    public void deleteChatMessage(JoinChatVO vo) {
        chatMapper.deleteChatMessage(vo);
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
    public ArrayList<GroupVO> getMyGroup(String userId) {
        return chatMapper.getMyGroup(userId);
    }

    @Override
    public ArrayList<JoinGroupVO> getMyStudent(GroupVO vo) {
        return chatMapper.getMyStudent(vo);
    }

    @Override
    public ArrayList<ChatMessageVO> loadChatting(RoomVO vo) {
        return chatMapper.loadChatting(vo);
    }

    @Override
    public int saveMessage(List<ChatMessageVO> list) {
        return chatMapper.saveMessage(list);
    }

    @Override
    public int isAlreadyCreate(String myUserId, String otherUserId) {
        return chatMapper.isAlreadyCreate(myUserId, otherUserId);
    }

    @Override
    public void oneToOneJoinChat(JoinChatVO vo, String myUserId) {
       if(vo.getUser_id() != null){
           vo.setJc_status("비활성화");
           chatMapper.oneToOneJoinChat(vo);
       }
       vo.setUser_id(myUserId);
       vo.setJc_status("비활성화");
       chatMapper.oneToOneJoinChat(vo);
    }

    @Override
    public void disActivateChatStatus(String userId, RoomVO vo) {
        if(vo.getRc_usage().equals("one")){
            chatMapper.disActivateChatStatus(userId, vo.getRc_no());
        }
    }
}
