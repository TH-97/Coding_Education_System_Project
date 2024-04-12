package com.project.geomin.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomVO{
	private String rc_no;
	private String rc_title;
	private Integer rc_num;
	private String rc_usage;

	//임시방편
	private String roomNumber;
	private String roomName;


}
