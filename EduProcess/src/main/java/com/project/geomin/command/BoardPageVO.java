package com.project.geomin.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.project.geomin.board.service.Criteria;

import lombok.Data;

@Data
public class BoardPageVO {
	private int start; //게시판 화면에 보여질 첫페이지 번호
	private int end; //게시판 화면에 보여질 끝페이지 번호
	private boolean prev; //이전 버튼 활성화 여부
	private boolean next; // 다음페이지 버튼 활성화 여부
	
	private int currentPage; //현재 페이지 번호 (cri)
	private int writing; //몇개의 데이터를 보여줄지(cri)
	private int total; //총 게시물 수
	
	private int realEnd; //진짜 끝 번호
	private List<Integer> pageList; //페이지 번호리스트(타임리프에선 향상된 for문을 위한 List)
	
	private Criteria cri; //페이징 기준
	
	public BoardPageVO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		this.currentPage = cri.getCurrentPage();
		this.writing = cri.getWriting();
		
		this.end=(int)(Math.ceil(this.currentPage/10.0)*10);
		System.out.println("end : " +this.end);
		this.start = end-10+1;
		
		this.realEnd=(int)Math.ceil(total/(double)this.writing);
		
		if(this.end>realEnd) this.end = realEnd;
		
		this.prev = this.start>1;
		
		this.next = realEnd>this.end;
		
		this.pageList = IntStream.rangeClosed(this.start,this.end).boxed().collect(Collectors.toList());
		System.out.println("pageList : "+this.pageList);
		
	}
}
