package com.project.geomin.chat.service;

import com.project.geomin.command.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ChatMapper {
    int CreateChatRoom(JoinChatVO vo);

    int oneToOneJoinChat(JoinChatVO vo);

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

    //이미 존재하는방 체크
    int isAlreadyCreate(@Param("myUserId") String myUserId, @Param("otherUserId") String otherUserId);

    //나가기전 채팅내용 저장
    int saveMessage(List<ChatMessageVO> list);
    int insertChatMessage(ChatMessageVO vo);
    int activateChatStatus(ChatMessageVO vo);

    void disActivateChatStatus(@Param("userId") String userId, @Param("rcNo") String rcNo);
    //////////////////////////////
    //그룹채팅 생성
    int groupChatCreate(JoinChatVO vo);
    //그룹채팅 연결
    int groupChatJoin(JoinChatVO vo);

    String getRcNo(JoinChatVO vo);

    void joinChatGroupDelete(JoinChatVO vo);
    void joinChatOneDelete(JoinChatVO vo);
    void chatRoomDelete(JoinChatVO vo);
    void deleteChatMessage(JoinChatVO vo);


}
