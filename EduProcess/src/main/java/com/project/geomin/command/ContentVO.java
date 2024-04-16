package com.project.geomin.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentVO {
    private String con_nm;
    private Integer cate_no;
    private Integer con_price;
    private Integer con_discount;
    private String con_description;
    private String con_lv;
    private String thumbnail;
    private String thumbnail_path;
    private String service_class;
    private String cate_nm;
}
