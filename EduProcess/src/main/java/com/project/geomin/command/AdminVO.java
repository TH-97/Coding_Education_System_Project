package com.project.geomin.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminVO {
    //여기는 컨텐츠
    private String con_nm;
    private String cate_no;
    private int con_price;
    private int con_discount;
    private String con_description;
    private String con_lv;
    private String thumbnail;
    private String thumbnail_path;
    private String service_class;
    //여기는 파일

    private String file_nm;
    private String file_path;


}
