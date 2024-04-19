package com.project.geomin.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupAnswerVO {
    private Integer ag_no;
    private Integer qg_no;
    private String user_id;
    private String ag_content;
    private String ag_date;
}
