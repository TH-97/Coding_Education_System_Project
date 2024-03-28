package com.project.geomin.user.service;

import com.project.geomin.user.command.UserVO;

public interface UserService {

	public UserVO login(String username);
	public int join(UserVO userVO);
}
