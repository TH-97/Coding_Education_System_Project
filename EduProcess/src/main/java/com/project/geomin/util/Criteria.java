package com.project.geomin.util;

import lombok.Data;

@Data
public class Criteria {

    private int page;	//페이지번호
    private int amount; //데이터개수

    public Criteria() {
        this.page = 1;
        this.amount = 10;
    }

    public Criteria(int page, int amount) {
        super();
        this.page = page;
        this.amount = amount;
    }

    public int getPageStart() {
        return (page - 1) * amount;
    }

}
