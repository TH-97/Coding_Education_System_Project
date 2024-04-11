package com.project.geomin.admin.service;

import com.project.geomin.command.AdminVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
    public void insertInformation(@Param("con_nm")String con_nm,
                                  @Param("cate_no")String cate_no,
                                  @Param("con_price")Integer con_price,
                                  @Param("con_discount")Integer con_discount,
                                  @Param("con_description")String con_description,
                                  @Param("con_lv")String con_lv,
                                  @Param("thumbnail")String thumbnail,
                                  @Param("thumbnail_path")String thumbnail_path,
                                  @Param("service_class")String service_class);

    void insertInformation2(@Param("con_nm")String con_nm,
                            @Param("file_nm")String file_nm,
                            @Param("file_path")String file_path,
                            @Param("service_class1")String service_class1);

    public AdminVO getT(@Param("content_name")String con_nm);
    public List<AdminVO> getF(@Param("content_name")String con_nm);

    public List<AdminVO> getContent();

}
