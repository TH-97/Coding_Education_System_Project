package com.project.geomin.review.service;

import com.project.geomin.command.ReviewVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewService {
    public void inputReview(@Param("textarea")String review_context,
                            @Param("reviewStar")int reviewStar,
                            @Param("content_name")String con_nm);

    public List<ReviewVO> getReview(@Param("content_name")String con_nm);
}
