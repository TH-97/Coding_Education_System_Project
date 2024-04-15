package com.project.geomin.student.service;

import com.project.geomin.command.GroupSearchVO;
import com.project.geomin.command.GroupVO;
import com.project.geomin.command.WorkVO;
import com.project.geomin.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface WorkMapper {
    public ArrayList<WorkVO> getList(@Param("cri") Criteria cri, @Param("user_id") String user_id);
    public int getTotal(@Param("cri") Criteria cri, @Param("user_id") String user_id); //전체게시글 수
    ArrayList<GroupVO> selectGroup(@Param("gvo") GroupVO gvo, @Param("gsvo") GroupSearchVO gsvo);
    public int regist(WorkVO vo);
    public WorkVO getDetail(int user_id);
}
