package com.project.geomin.user.service;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.geomin.command.UserVO;

@Mapper
public interface UserMapper {

	//user
	public UserVO login(String username);
	public int join(UserVO userVO);
	
	//auth
	public int auth(Map<String,Object> map);
	public UserVO aLogin(String pn);
	public UserVO authCheck(@Param("auth_nm") String auth_nm , @Param("user_pn") String user_pn);
}
