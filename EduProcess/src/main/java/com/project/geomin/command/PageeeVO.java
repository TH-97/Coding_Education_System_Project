package com.project.geomin.command;

import com.project.geomin.util.Criteria;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageeeVO {
    private int start; //페이지네이션 시작번호
    private int end; //페이지네이션 끝번호
    private boolean prev; //이전버튼 활성화여부
    private boolean next; //다음버튼 활성화여부

    private int page; //현재조회하는 페이지번호 (criteria가 가지고있음)
    private int amount; //조회하는 데이터개수 (criteria가 가지고있음)
    private int total; //전체데이터 개수

    private int realEnd; //진짜 끝번호

    private Criteria cri; //페이지기준
    private List<Integer> pageList;

    public PageeeVO(Criteria cri, int total) {
        this.page = cri.getPage();
        this.amount = cri.getAmount();
        this.total = total;
        this.cri = cri;

        this.end = (int)(Math.ceil( this.page / 10.0 )) * 10; //페이지네이션 개수

        this.start = this.end - 10 + 1;

        this.realEnd = (int)(Math.ceil( this.total / (double)this.amount ) );

        if(this.end > this.realEnd ) {
            this.end = this.realEnd;
        }

        this.prev = this.start > 1;

        this.next = this.realEnd > this.end;

        this.pageList = IntStream.rangeClosed(this.start, this.end)
                .boxed()
                .collect(Collectors.toList());

    }
}
