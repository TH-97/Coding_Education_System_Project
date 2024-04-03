package com.project.geomin.user.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.geomin.command.UserVO;

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

	@Override
	public int auth(Map<String,Object> map) {
		int a =userMapper.auth(map);
		
		return a;
	}

	@Override
	public UserVO aLogin(String pn) {
		return userMapper.aLogin(pn);
	}

	@Override
	public UserVO authCheck(String auth_nm , String user_pn) {
		return userMapper.authCheck(auth_nm ,user_pn);
	}

	@Override
	public UserVO checkLogin(String username) {
		
		return userMapper.checkLogin(username);
	}


	
}
