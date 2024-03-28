package com.project.geomin.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.project.geomin.handler.CustomRememberMeHandler;
import com.project.geomin.handler.CustomSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
	private MyUserDetailsService myuserDetails;

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public WebSecurityCustomizer websecuritycustomizer() {
//		 return (web)-> web.ignoring()
//	       .requestMatchers(PathRequest.toStaticResources().atCommonLocations()) // 기본 정적 리소스 무시
//	       .requestMatchers(
//	            new AntPathRequestMatcher("/css/**"),
//	            new AntPathRequestMatcher("/js/**"));
//	}
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//	}
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
    	return web -> web.ignoring()
        	.requestMatchers(PathRequest
            	.toStaticResources()
                .atCommonLocations())	
                .antMatchers("/css/**","/js/**","/img/**");
                 
     }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		AuthenticationManager authenticationManager = 
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));

		

		http
		.csrf().disable()
		.headers().frameOptions().sameOrigin()
		.and()
		.authorizeHttpRequests()
//		.antMatchers("/images/**","/js/**","/css/**").permitAll()
		.antMatchers("/user/login","/user/join","/user/register","/loginForm","/user/stud_join","/user/educ_join").permitAll()
		.antMatchers("/admin/**").hasAnyRole("ADMIN","MASTER")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/user/login") // 로그인 페이지 URL
		.loginProcessingUrl("/loginForm") // 로그인 처리 URL
//		.defaultSuccessUrl("/user/user_main") // 로그인 성공 후 이동할 URL
		.successHandler( loginSuccessHandler() )
		.failureUrl("/user/login?err=true") // 로그인 실패 후 이동할 URL
		.and()
		.exceptionHandling().accessDeniedPage("/user/user_deny")
		.and()	
		.logout().logoutUrl("/logout").logoutSuccessUrl("/user/login");
		
		http.rememberMe()
		.key("geomin") //리멤버미를 쿠키로 동작시키는데 그때, 쿠키에 저장되는 토큰값을 만들 비밀키
		.tokenValiditySeconds(604800) //1주일 동안 유효한 토큰
		.rememberMeParameter("remember-me") //화면에서 전달되는 checkbox 파라미터 값
		.userDetailsService(myuserDetails) //리멤버미 토큰이 있을때 실행시킬 로그인시도 서비스
		.authenticationSuccessHandler(authenticationSuccessHandler());
//		.and()
//		.addFilter(new CustomLoginFilter(authenticationManager));
		


		return http.build();
	}

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new	CustomSuccessHandler();
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomRememberMeHandler();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
}
