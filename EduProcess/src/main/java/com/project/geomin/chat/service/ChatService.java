package com.project.geomin.chat.service;

import com.project.geomin.command.GroupVO;
import com.project.geomin.command.JoinChatVO;
import com.project.geomin.command.RoomVO;

import java.util.ArrayList;

public interface ChatService {
    int CreateChatRoom(JoinChatVO vo);
    //내가 속한 채팅방 조회
    ArrayList<RoomVO> selectmyRoom(String userId);

    //나의 그룹 교육자 조회
    ArrayList<GroupVO> getMyTeacher(String userId);

    void oneToOneJoinChat(JoinChatVO vo, String myUserId);
}
