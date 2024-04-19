package com.project.geomin.review.service;

import com.project.geomin.command.ReviewVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Override
    public void inputReview(String con_nm,
                            int star,
                            String review_context,
                            String user_id){
        reviewMapper.inputReview(con_nm,star,review_context,user_id);
    }

    @Override
    public List<ReviewVO> getReview(String con_nm) {
        return reviewMapper.getReview(con_nm);
    }
}
