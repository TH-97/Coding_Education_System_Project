package com.project.geomin.board.service;

import java.util.ArrayList;

import com.project.geomin.command.AnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.geomin.command.QuestionVO;

@Mapper
public interface BoardMapper {
	public int boardRegi(QuestionVO vo);
	public ArrayList<QuestionVO> questionList(@Param("cri") Criteria cri, @Param("questionSearch") String questionSearch, @Param("status") String status);
	int getBoardPageTotal(@Param("questionSearch") String questionSearch, @Param("status") String status);
	QuestionVO getboardDetail(QuestionVO vo);
	ArrayList<AnswerVO> getAnswerList(QuestionVO vo);
	int QuestionUpdateReal(QuestionVO vo);
	int delQuestion(QuestionVO vo);
	int registAnswer(AnswerVO vo);
	int delAnswer(AnswerVO vo);
	int delQuestionAnswer(QuestionVO vo);
}
