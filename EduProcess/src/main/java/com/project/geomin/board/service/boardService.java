package com.project.geomin.board.service;

import java.util.ArrayList;

import com.project.geomin.command.QuestionVO;

public interface boardService {
	public int boardRegi(QuestionVO vo);
	public ArrayList<QuestionVO> questionList(Criteria cri);
}
