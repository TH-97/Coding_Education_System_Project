package com.project.geomin.edu.service;

import com.project.geomin.command.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("eduservice")
public class EduServiceImpl implements EduService{
    @Autowired
    private EduMapper eduMapper;
    @Override
    public void groupRegist(GroupVO vo) {
        eduMapper.groupRegist(vo);
    }

    @Override
    public void studentApply(JoinGroupVO vo) {
        eduMapper.studentApply(vo);
    }

    @Override
    public int getSgNo() {
        return eduMapper.getSgNo();
    }

    @Override
    public  ArrayList<JoinGroupVO> selectStudent(JoinGroupVO vo) {
        return eduMapper.selectStudent(vo);
    }
    @Override
    public ArrayList<GroupVO> selectGroup(GroupVO vo, GroupSearchVO gsvo) {
        return eduMapper.selectGroup(vo, gsvo);
    }

    @Override
    public ArrayList<GroupVO> selectGroupPK(GroupVO vo) {
        return eduMapper.selectGroupPK(vo);
    }

    @Override
    public ArrayList<JoinGroupVO> myJoinGroup(Criteria cri, String userId, GroupSearchVO searchVO) {
        return eduMapper.myJoinGroup(cri, userId, searchVO);
    }

    @Override
    public ArrayList<UserVO> studentSelect(UserVO vo) {
        return eduMapper.studentSelect(vo);
    }

    @Override
    public void groupUpdateReal(GroupVO vo) {
        eduMapper.groupUpdateReal(vo);
    }

    @Override
    public void deleteJoinStud(JoinGroupVO vo) {
        eduMapper.deleteJoinStud(vo);
    }

    @Override
    public void applierApply(Map<String, Object> map) {
        String jgConfirm = (String) map.get("updateStatus");
        for(String str :map.keySet()){
            if(str.equals("updateStatus"))
                break;
           for(List<String> s : (List<List<String>>)map.get(str)){
               String userId = (String)s.get(0);
               String sgNo = (String)s.get(1);
                   eduMapper.applierApply(userId, sgNo, jgConfirm);

           }

        }
    }

    @Override
    public void groupLeaderApply(JoinGroupVO vo) {
        eduMapper.groupLeaderApply(vo);
    }

    @Override
    public void groupDelete(String sgNo) {
        eduMapper.groupDelete(sgNo);
    }

    @Override
    public void joinGroupDelete(String sgNo) {
        eduMapper.joinGroupDelete(sgNo);
    }

    @Override
    public int selectPageTotal(String userId, GroupSearchVO searchVO) {
        return eduMapper.selectPageTotal(userId, searchVO);
    }

    @Override
    public int noSearchSelectPageTotal(String userId) {
        return eduMapper.noSearchSelectPageTotal(userId);
    }

    @Override
    public List<ContentVO> selectMyContents(Criteria cri, String userId, GroupSearchVO searchVO) {
        return eduMapper.selectMyContents(cri, userId, searchVO);
    }

    @Override
    public int contentPageTotal(String userId, GroupSearchVO searchVO) {
        return eduMapper.contentPageTotal(userId, searchVO);
    }


}



