package com.project.geomin.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupQAVO {
    private Integer qg_no;
    private Integer sg_no;
    private String user_id;
    private String qg_title;
    private String qg_content;
    private String qg_date;
    private Integer ag_count;

}
