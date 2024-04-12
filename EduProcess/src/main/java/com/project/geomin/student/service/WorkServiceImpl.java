package com.project.geomin.student.service;

import com.project.geomin.command.WorkVO;
import com.project.geomin.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("workService")
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkMapper workMapper;

    @Override
    public ArrayList<WorkVO> getList(Criteria cri, String user_id) {

        ArrayList<WorkVO> al = workMapper.getList(cri, user_id);
        System.out.println(al.toString());
        return al;
    }

    @Override
    public int getTotal(Criteria cri, String user_id) {
        return workMapper.getTotal(cri, user_id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int regist(WorkVO vo) {

        int result = workMapper.regist(vo); //

        System.out.println("실행됩니다");
        System.out.println(result);
        return result;
    }
    public WorkVO getDetail(int user_id) {
        return workMapper.getDetail(user_id);
    }
}
