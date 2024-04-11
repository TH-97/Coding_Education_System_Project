package com.project.geomin.service;

import com.project.geomin.command.WorkVO;
import com.project.geomin.util.Criteria;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface WorkService {
    public ArrayList<WorkVO> getList(Criteria cri, String user_id);
    public int getTotal(Criteria cri, String user_id);
    public int regist(WorkVO vo);
    public WorkVO getDetail(int user_id);
}
