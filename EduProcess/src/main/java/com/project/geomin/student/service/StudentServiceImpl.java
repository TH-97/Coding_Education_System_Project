package com.project.geomin.student.service;


import com.project.geomin.command.GroupSearchVO;
import com.project.geomin.command.GroupVO;
import com.project.geomin.command.JoinGroupVO;
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
            if(joinGroupVO.getJg_confirm().equals("승인") && joinGroupVO.getSg_status().equals("학습진행중")){
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


}
