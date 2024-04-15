package com.project.geomin.board.service;



import lombok.Data;


@Data
public class Criteria {
    private Integer currentPage;
    private Integer writing;

    public Criteria(){
        this(1,10);
    }

    public Criteria(Integer currentPage, Integer writing) {
        this.currentPage = currentPage;
        this.writing = writing;
    }

    public Integer getStartPage(){
        return  (currentPage - 1) * writing ;
    }

}
