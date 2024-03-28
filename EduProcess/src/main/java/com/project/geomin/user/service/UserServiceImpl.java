package com.project.geomin.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.geomin.user.command.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;

	@Override
	public UserVO login(String username) {
		UserVO vo = userMapper.login(username);
		return vo;
	}

	@Override
	public int join(UserVO userVO) {
		int a = userMapper.join(userVO);
		return a;
	}


	
}
