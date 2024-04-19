package com.project.geomin.review.service;

import com.project.geomin.command.ReviewVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewService {
    public void inputReview(@Param("con_nm")String con_nm,
                            @Param("star")int star,
                            @Param("review_context")String review_context,
                            @Param("user_id")String user_id);

    public List<ReviewVO> getReview(@Param("con_nm")String con_nm);
}
