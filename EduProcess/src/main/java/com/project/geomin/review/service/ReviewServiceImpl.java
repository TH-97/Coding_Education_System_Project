package com.project.geomin.review.service;

import com.project.geomin.command.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Override
    public void inputReview(String review_context, int reviewStar,String con_nm) {
        reviewMapper.inputReview(review_context,reviewStar,con_nm);
    }

    @Override
    public List<ReviewVO> getReview(String con_nm) {
        return reviewMapper.getReview(con_nm);
    }
}
