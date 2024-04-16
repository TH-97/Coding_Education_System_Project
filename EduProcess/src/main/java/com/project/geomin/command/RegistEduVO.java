package com.project.geomin.command;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistEduVO {

	//강사등록
	String company;
	String position;
	String duration;
}
