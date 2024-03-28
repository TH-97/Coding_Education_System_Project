package com.project.geomin.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.geomin.command.UserVO;
import com.project.geomin.user.service.UserMapper;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserMapper userMapper;

	//loginProcessingUrl에 로그인URL을 등록
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//로그인 시도
		UserVO userVO = userMapper.login(username);
		
		if(userVO != null) {
			return new MyUserDetails(userVO);
		}
		
		return null;
	}
	
	
}
