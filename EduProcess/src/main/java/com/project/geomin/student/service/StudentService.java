package com.project.geomin.student.service;

import com.project.geomin.command.*;
import com.project.geomin.edu.service.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Map;

public interface StudentService {
    ArrayList<GroupVO> allGroupSelect(Criteria cri, GroupSearchVO searchVO);
    ArrayList<JoinGroupVO> myApplyGroup(String userId);
    ArrayList<JoinGroupVO> myJoinGroup(String userId);

    void groupApply(JoinGroupVO vo);
    int groupAplyTotal(JoinGroupVO vo);
    int groupAplyCheck(JoinGroupVO vo);
    int groupMaxAplyCheck(String userId);

    int selectGroupPageTotal(GroupSearchVO searchVO);

    int deleteAply(JoinGroupVO vo);

    ArrayList<GroupNoticeVO> getNoticeList(String sgNo);
    ArrayList<GroupNoticeVO> getRecordNoticeList(String sgNo);
    ArrayList<GroupQAVO> getQuestionList(String sgNo);
    GroupNoticeVO getNoticeDetail(String ngNo);
    int noticeRegist(GroupNoticeVO vo);
    int noticeUpdate(GroupNoticeVO vo);
    int noticeDelete(GroupNoticeVO vo);

    int QARegist(GroupQAVO vo);

}
