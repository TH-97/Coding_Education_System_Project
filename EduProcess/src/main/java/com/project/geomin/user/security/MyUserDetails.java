package com.project.geomin.user.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.project.geomin.command.UserVO;

public class MyUserDetails implements UserDetails ,OAuth2User{

	//vo 아마 userVO
	private UserVO userVO;
	private Map<String , Object> attributes;

	public MyUserDetails(UserVO vo) {
		this.userVO = vo;
	}
	
	public MyUserDetails(UserVO vo , Map<String,Object> attributes) {
		this.userVO = vo;
		this.attributes = attributes;
		System.out.println("user VO : " + vo );
		System.out.println("userdetail : " + attributes);
	}


	public UserVO getUserVO() {
		return userVO;
	}


	public String getRole() {
		return userVO.getRole();
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		ArrayList<GrantedAuthority> authorities= new ArrayList<>();

		authorities.add(() -> userVO.getRole());

		return authorities;
	}

	@Override
	public String getPassword() {
		return userVO.getUser_pw();
	}

	@Override
	public String getUsername() {
		return userVO.getUser_id();
	}
	
	

	@Override
	public boolean isAccountNonExpired() {
		//휴먼계정
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		//로그인 시도 횟수
		boolean bool = true;
//		if(userVO.getLogin_tc()>4) {
//			bool = false;
//		}
		return bool;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		//회원탈퇴
		boolean bool = true;
//		if(userVO.getLogin_ena().equals("F")) {
//			bool = false;
//		}

		return bool;
	}


	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}


	@Override
	public String getName() {
		return userVO.getUser_nm();
	}

}
