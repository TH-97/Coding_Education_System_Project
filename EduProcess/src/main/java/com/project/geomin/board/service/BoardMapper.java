package com.project.geomin.board.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.geomin.command.QuestionVO;

@Mapper
public interface BoardMapper {
	public int boardRegi(QuestionVO vo);
	public ArrayList<QuestionVO> questionList(@Param("cri") Criteria cri);
}
