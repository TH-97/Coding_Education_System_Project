package com.project.geomin.user.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.geomin.command.UserVO;
import com.project.geomin.user.jwt.util.JWTService;
import com.project.geomin.user.service.KakaoAPI;
import com.project.geomin.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	@Qualifier("kakao")
	private KakaoAPI kakaoAPI;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bc;

	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
	@GetMapping("/stud_join")
	public String stud_join() {
		return "user/stud_join";
	}
	
	@GetMapping("/educ_join")
	public String educ_join() {
		return "user/educ_join";
	}

	@PostMapping("/register")
	public String register(UserVO userVO,Model model) {

		String pw = bc.encode(userVO.getUser_pw());
		userVO.setUser_pw(pw);
		int a = userService.join(userVO);

		if(a==1) {
			return "user/success";
		}else {
			return "user/alert";

		}
	}

//	@GetMapping("/login")
//	public String login() {
//		return"user/login";
//	}

//	@PostMapping("/loginForm")
//	public ResponseEntity<Object> login(@RequestBody StudentVO vo) {
//
//		String token = JWTService.createToken(vo.getStud_id());
//
//		return new ResponseEntity<>(token , HttpStatus.OK );
//	}
	
	@GetMapping("/login")
	public String login(@RequestParam(value = "err", required= false)String err 
			,Model model){

		System.out.println(err);
		if(err != null) {
			model.addAttribute("msg" , "아이디와 비밀번호를 확인해주세요");
		}


		return "user/login";

	}

	@GetMapping("/mainPage")
	public String mainPage(){
		return "user/main_page";
	}
	
	@GetMapping("/kakao")
	public String kakao(@RequestParam("code") String code,Model model) {
		System.out.println("코드 : " + code);
		model.addAttribute("code",code);
		
		String access_token = kakaoAPI.getAccessToken(code);
		System.out.println("접근토큰:" + access_token);
		
		HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(access_token);
		System.out.println("결과:" + userInfo.toString());
		
		
		
		return "user/kakao";
	}
}
