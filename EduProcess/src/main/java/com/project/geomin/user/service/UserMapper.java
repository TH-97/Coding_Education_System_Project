package com.project.geomin.user.service;

import org.apache.ibatis.annotations.Mapper;

import com.project.geomin.command.UserVO;

@Mapper
public interface UserMapper {

	public UserVO login(String username);
	public int join(UserVO userVO);
}
