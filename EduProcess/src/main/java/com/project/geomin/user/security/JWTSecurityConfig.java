package com.project.geomin.user.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.project.geomin.user.filter.CustomLoginFilter;
import com.project.geomin.user.filter.JWTAuthoticationFilter;

//@Configuration
//@EnableWebSecurity
public class JWTSecurityConfig {

	@Bean 
	public BCryptPasswordEncoder bc() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		
		AuthenticationManager authenticationManager = 
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
		http
		.csrf().disable()
		.formLogin().disable()
		.httpBasic().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.cors(Customizer.withDefaults())
		.authorizeRequests(authorize -> authorize.antMatchers("/user").permitAll())
		.addFilter(new JWTAuthoticationFilter(authenticationManager));

		//로그인 
//		http.requestMatchers().antMatchers("/loginForm")
//		.and()
//		.addFilter(new CustomLoginFilter(authenticationManager));
		//회원가입에는 필터x

		//로그인(인증된사람은 addFilter)
//		http.requestMatchers().antMatchers("/user/**","/admin/**")
//		.and()
//		.addFilter(new JWTAuthoticationFilter(authenticationManager));


		return http.build();
	}
	@Bean
	CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
