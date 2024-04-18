package com.project.geomin.student.service;


import com.project.geomin.command.*;
import com.project.geomin.edu.service.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service("StudentService")
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public ArrayList<GroupVO> allGroupSelect(Criteria cri, GroupSearchVO searchVO) {
        return studentMapper.allGroupSelect(cri, searchVO);
    }

    @Override
    public ArrayList<JoinGroupVO> myApplyGroup(String userId) {
        return studentMapper.myApplyGroup(userId);
    }

    @Override
    public ArrayList<JoinGroupVO> myJoinGroup(String userId) {
        ArrayList<JoinGroupVO> vo = studentMapper.myApplyGroup(userId);
        System.out.println(vo);
        ArrayList<JoinGroupVO> newVO = new ArrayList<>();
        for(JoinGroupVO joinGroupVO : vo){
            if(joinGroupVO.getJg_confirm().equals("승인")){
                newVO.add(joinGroupVO);
            }
        }

        return newVO;
    }

    @Override
    public void groupApply(JoinGroupVO vo) {
        studentMapper.groupApply(vo);
    }

    @Override
    public int groupAplyTotal(JoinGroupVO vo) {
        return studentMapper.groupAplyTotal(vo);
    }

    @Override
    public int groupAplyCheck(JoinGroupVO vo) {
        return studentMapper.groupAplyCheck(vo);
    }

    @Override
    public int groupMaxAplyCheck(String userId) {
        return studentMapper.groupMaxAplyCheck(userId);
    }

    @Override
    public int selectGroupPageTotal(GroupSearchVO searchVO) {
        return studentMapper.selectGroupPageTotal( searchVO);
    }

    @Override
    public int deleteAply(JoinGroupVO vo) {
        return studentMapper.deleteAply(vo);
    }

    @Override
    public ArrayList<GroupNoticeVO> getNoticeList(String sgNo) {
        return studentMapper.getNoticeList(sgNo);
    }

    @Override
    public ArrayList<GroupNoticeVO> getRecordNoticeList(String sgNo) {
        return studentMapper.getRecordNoticeList(sgNo);
    }

    @Override
    public ArrayList<GroupQAVO> getQuestionList(String sgNo) {
        return studentMapper.getQuestionList(sgNo);
    }

    @Override
    public GroupNoticeVO getNoticeDetail(String ngNo) {
        return studentMapper.getNoticeDetail(ngNo);
    }

    @Override
    public int noticeRegist(GroupNoticeVO vo) {
        return studentMapper.noticeRegist(vo);
    }

    @Override
    public int noticeUpdate(GroupNoticeVO vo) {
        return studentMapper.noticeUpdate(vo);
    }

    @Override
    public int noticeDelete(GroupNoticeVO vo) {
        return studentMapper.noticeDelete(vo);
    }

    @Override
    public int QARegist(GroupQAVO vo) {
        return studentMapper.QARegist(vo);
    }


}
