package com.project.geomin.user.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.project.geomin.user.jwt.util.JWTService;

public class JWTAuthoticationFilter extends BasicAuthenticationFilter{

	public JWTAuthoticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//헤더 값 얻기
		String header = request.getHeader("Authorization");
		//헤더가 없거나 Bearer로 시작하지 않으면
		if(header == null || header.startsWith("Bearer ") == false ) {

			response.setContentType("text/plain; charset=UTF8");
			response.sendError(403, "토큰이 없습니다.");		

			return; //함수종료
		}
		try {
			String token = header.substring( 7 ); //Bearer공백 이후
			boolean result = JWTService.validateToken(token); //토큰유효성검사

			if(result) {
				chain.doFilter(request, response); //다음필터로 연결
			} else {
				response.setContentType("text/html; charset=UTF8;");
				response.sendError(403, "토큰이 만료되었습니다");	

			}
		} catch (Exception e) {
			response.setContentType("text/html; charset=UTF8;");
			response.sendError(403, "토큰이 위조되었습니다");	
		}

	}
}



