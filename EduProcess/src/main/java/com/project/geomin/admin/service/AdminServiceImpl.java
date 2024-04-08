package com.project.geomin.admin.service;

import com.project.geomin.command.AdminVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public void insertInformation(String con_nm,
                                  String cate_no,
                                  Integer con_price,
                                  Integer con_discount,
                                  String con_description,
                                  String con_lv,
                                  String thumbnail,
                                  String thumbnail_path,
                                  String service_class) {
        adminMapper.insertInformation(con_nm,
                cate_no,
                con_price,
                con_discount,
                con_description,
                con_lv,
                thumbnail,
                thumbnail_path,
                service_class);
    }

    @Override
    public void insertInformation2(
            String con_nm,
            String file_nm,
            String file_path,
            String service_class1
    ) {
        adminMapper.insertInformation2(con_nm,file_nm,file_path,service_class1);
    }

    @Override
    public AdminVO getT(AdminVO vo) {
        return adminMapper.getT(vo);
    }

    @Override
    public AdminVO getF(AdminVO vo) {
        return adminMapper.getF(vo);
    }

}
