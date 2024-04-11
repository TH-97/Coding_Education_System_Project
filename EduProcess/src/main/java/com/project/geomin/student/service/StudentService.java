package com.project.geomin.student.service;

import com.project.geomin.command.GroupSearchVO;
import com.project.geomin.command.GroupVO;
import com.project.geomin.command.JoinGroupVO;
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

}
