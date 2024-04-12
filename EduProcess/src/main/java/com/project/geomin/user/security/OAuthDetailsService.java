package com.project.geomin.user.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.project.geomin.command.UserVO;
import com.project.geomin.user.service.UserMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OAuthDetailsService extends DefaultOAuth2UserService{

	private BCryptPasswordEncoder bc;
	
	@Autowired
	private UserMapper userMapper ;
	
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		// OAuth2 서비스 id 코드구분( 카카오,구글,네이버 등)
		String provider = userRequest.getClientRegistration().getRegistrationId(); 
		System.out.println("provider : " +provider);
		
		
	    String providerId = oauth2User.getAttribute("sub");
	    String loginId = "#" + providerId;
	    
	    System.out.println("loginId : " +loginId);
	    
	    UserVO vo = userMapper.login(loginId);
	    
	    if(vo == null) {
            vo = UserVO.builder()
                    .user_id(loginId)
                    .user_nm(oauth2User.getAttribute("name"))
                    .role("ROLE_stud")
                    .build();
        } 
	    
	    
		return new MyUserDetails(vo , oauth2User.getAttributes());
		
	}
	
	
}
