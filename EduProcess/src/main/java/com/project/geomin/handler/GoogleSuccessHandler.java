package com.project.geomin.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.ui.Model;

import com.project.geomin.command.UserVO;
import com.project.geomin.user.security.MyUserDetails;
import com.project.geomin.user.service.UserMapper;


public class GoogleSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	UserMapper userMapper;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		MyUserDetails details =(MyUserDetails)authentication.getPrincipal();
		
		System.out.println("구글이름: " +details.getUsername());
		
		
		String role =details.getRole();
		System.out.println("구글로그인성공: "+ role);
		UserVO vo = userMapper.login(details.getUsername());
		
		if(vo ==null) {
			UserVO vo1 = UserVO.builder()
						.user_id(details.getUsername())
						.user_nm(details.getUsername())
						.build();
			System.out.println("vo : " +vo1);
			
			response.sendRedirect("/user/GoogleJoin");
			return;
		}
		
		
		if(role.equals("ROLE_educ")) {
			response.sendRedirect("/user/mainPage");
			
		}else if(role.equals("ROLE_stud")) {
			response.sendRedirect("/user/mainPage");
		}else {
			response.sendRedirect("/admin");
		}
		
		
	}

}
