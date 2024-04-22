package com.project.geomin.student.service;

import com.project.geomin.command.GroupSearchVO;
import com.project.geomin.command.GroupVO;
import com.project.geomin.command.UserVO;
import com.project.geomin.command.WorkVO;
import com.project.geomin.util.Criteria;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface WorkService {
    public ArrayList<WorkVO> getList(Criteria cri, String user_id);
    public ArrayList<WorkVO> getList2(Criteria cri, String user_id);
    public ArrayList<WorkVO> getList3(Criteria cri, String user_id);
    ArrayList<GroupVO> selectGroup(GroupVO vo, GroupSearchVO gsvo);
    ArrayList<UserVO> userList(UserVO vo);
    public int getTotal(Criteria cri, String user_id);
    public int getTotal2(Criteria cri, String user_id);
    public int regist(WorkVO vo);
    public int insertHw(String h_no,String sg_no);
    public WorkVO getDetail(int h_no);
    public void delete(int h_no);

}
