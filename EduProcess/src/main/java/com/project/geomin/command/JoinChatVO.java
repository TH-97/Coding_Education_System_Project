package com.project.geomin.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinChatVO {
    //join_chat
    private String user_id;
    private String user_nm;
    private String rc_no;
    private String jc_status;

    //room_chat
    private Integer rc_num;
    private String rc_title;
    private String rc_usage;



}
