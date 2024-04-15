package com.project.geomin.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkVO {
    private Integer h_no;
    private String h_name;
    private String h_detail;
    private String h_level;
    private String h_reg_ymd;
    private String h_dead;
    private String sg_no; //fk
    private String sg_name;
}
