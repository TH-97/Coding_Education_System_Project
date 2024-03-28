package com.project.geomin.user.service;

import org.apache.ibatis.annotations.Mapper;

import com.project.geomin.user.command.UserVO;

@Mapper
public interface UserMapper {

	public UserVO login(String username);
	public int join(UserVO userVO);
}
