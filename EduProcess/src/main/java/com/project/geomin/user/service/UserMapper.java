package com.project.geomin.user.service;

import java.util.ArrayList;
import java.util.Map;

import com.project.geomin.command.WorkVO;
import com.project.geomin.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.geomin.command.UserVO;

@Mapper
public interface UserMapper {

	//user
	public UserVO login(String username);
	public int join(UserVO userVO);
	public UserVO checkLogin(String username);
	public void updatePW(@Param("user_pw") String user_pw,@Param("user_pn") String user_pn);
	
	//auth
	public int auth(Map<String,Object> map);
	public UserVO aLogin(String pn);
	public UserVO authCheck(@Param("auth_nm") String auth_nm , @Param("user_pn") String user_pn);


}
