package com.project.geomin.service;

import com.project.geomin.command.WorkVO;
import com.project.geomin.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.type.ArrayType;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
}
