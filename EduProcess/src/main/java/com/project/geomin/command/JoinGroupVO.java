package com.project.geomin.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinGroupVO {
    String user_id;
    String user_nm;
    Integer sg_no;
    String jg_confirm;
    Integer jg_score;

    //group table join
    String con_nm;
    String sg_user_id;
    String sg_user_nm;
    String sg_name;
    Integer sg_aplynum;
    Integer sg_allnum;
    String sg_start_date;
    String sg_end_date;
    String sg_level;
    String sg_content;
    String sg_status;
}
