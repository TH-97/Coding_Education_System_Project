package com.project.geomin.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.project.geomin.user.security.MyUserDetails;
import com.project.geomin.user.service.UserMapper;


public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		MyUserDetails details =(MyUserDetails)authentication.getPrincipal();
		
		String role =details.getRole();
		System.out.println("로그인성공: "+ role);
		
		
		if(role.equals("ROLE_educ")) {
			response.sendRedirect("/admin");
		}else if(role.equals("ROLE_stud")) {
			response.sendRedirect("/user/alert");
		}else {
			response.sendRedirect("/admin");
		}
		
		
	}

}
