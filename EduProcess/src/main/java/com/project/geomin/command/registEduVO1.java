package com.project.geomin.command;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class registEduVO1 {

	//강사등록
	String edu_name;
	String edu_sub;
	String company;
	String position;
	String duration;
}
