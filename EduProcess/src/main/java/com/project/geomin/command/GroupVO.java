package com.project.geomin.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupVO {
    String user_id;
    String user_nm;
    Integer ctb_no;
    String con_nm;
    String thumbnail_path;
    Integer sg_no;

    String sg_name;
    Integer sg_aplynum;
    Integer sg_allnum;
    String sg_start_date;
    String sg_end_date;
    String sg_level;
    String sg_content;
    String sg_status;

}
