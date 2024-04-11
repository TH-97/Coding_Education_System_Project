package com.project.geomin.edu.service;



import lombok.Data;


@Data
public class Criteria {
    private Integer currentPage;
    private Integer showPage;

    public Criteria(){
        this(1,10);
    }

    public Criteria(Integer currentPage, Integer showPage) {
        this.currentPage = currentPage;
        this.showPage = showPage;
    }

    public Integer getStartPage(){
        return  (currentPage - 1) * 10 ;
    }

}
