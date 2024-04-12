package com.project.geomin.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageVO{
    private Integer mc_no;
    private Integer rc_no;
    private String user_id;
    private String user_nm;
    private String mc_content;
    private String mc_send_date;

    private String rc_usage;
}
