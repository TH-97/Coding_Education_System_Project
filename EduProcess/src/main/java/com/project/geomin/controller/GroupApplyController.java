package com.project.geomin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupApplyController {
	
	@GetMapping("/groupRegist")
	public String groupRegist() {

		return "group/edu/groupRegist";
	}
	@GetMapping("/groupSelect")
	public String groupSelect(){
		return "group/edu/groupSelect";
	}

	@GetMapping("/groupJoin")
	public String groupJoin(){
		return "group/edu/groupJoin";
	}

}
