package com.project.geomin.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupNoticeVO {
    private Integer ng_no;
    private Integer sg_no;
    private String user_id;
    private String ng_title;
    private String ng_content;
    private String ng_date;
    private String ng_usage;
}
