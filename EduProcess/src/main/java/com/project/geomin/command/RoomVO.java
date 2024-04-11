package com.project.geomin.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomVO {
	String roomNumber;
	String roomName;
	Integer rc_rum;
	String rc_date;
	String rc_status;
	String rc_usage;
}
