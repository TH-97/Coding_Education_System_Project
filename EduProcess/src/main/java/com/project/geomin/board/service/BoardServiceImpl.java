package com.project.geomin.board.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.geomin.command.QuestionVO;

@Service("boardService")
public class BoardServiceImpl implements boardService {

	@Autowired
	BoardMapper boardMapper ;
	
	@Override
	public int boardRegi(QuestionVO vo) {
		int a = boardMapper.boardRegi(vo);
		return a;
	}

	@Override
	public ArrayList<QuestionVO> questionList(Criteria cri) {
		ArrayList<QuestionVO> list = boardMapper.questionList(cri);
		return list;
	}


}
