package com.project.geomin.user.service;

import java.util.Map;

import com.project.geomin.command.UserVO;

public interface UserService {

	//user
	public UserVO login(String username);
	public int join(UserVO userVO);
	
	//auth
	public int auth(Map<String,Object> map);
	public UserVO aLogin(String pn);
	public UserVO authCheck(String auth_nm , String user_pn);
}
