package com.project.geomin.chat.service;

import com.project.geomin.command.*;

import java.util.ArrayList;
import java.util.List;

public interface ChatService {
    int CreateChatRoom(JoinChatVO vo);

    //내가 속한 채팅방 조회
    ArrayList<RoomVO> selectmyRoom(String userId);

    //나의 그룹 교육자 조회
    ArrayList<GroupVO> getMyTeacher(String userId);
    //나의 그룹 조회
    ArrayList<GroupVO> getMyGroup(String userId);
    //나의 그룹의 학습자들 조회
    ArrayList<JoinGroupVO> getMyStudent(GroupVO vo);

    //채팅내역 로딩
    ArrayList<ChatMessageVO> loadChatting(RoomVO vo);
    //나가기전 채팅내용 저장
    int saveMessage(List<ChatMessageVO> list);

    //이미 존재하는방 체크
    int isAlreadyCreate(String myUserId, String otherUserId);
    //1대1 채팅 연결
    void oneToOneJoinChat(JoinChatVO vo, String myUserId);

    //입장시 비활성화
    void disActivateChatStatus(String userId, RoomVO vo);

    //그룹채팅 생성
    int groupChatCreate(JoinChatVO vo);
    //그룹채팅 연결
    int groupChatJoin(JoinChatVO vo);



    //방번호 조회
    String getRcNo(JoinChatVO vo);

}
