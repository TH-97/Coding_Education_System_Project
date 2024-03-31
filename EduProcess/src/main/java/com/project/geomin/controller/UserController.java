package com.project.geomin.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.project.geomin.user.security.MyUserDetails;
import com.project.geomin.user.service.KakaoAPI;
import com.project.geomin.user.service.NaverAPI;
import com.project.geomin.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	@Qualifier("kakao")
	private KakaoAPI kakaoAPI;
	
	@Autowired
	@Qualifier("naver")
	private NaverAPI naverAPI;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
//	private MyUserDetails myUserDetails;

	@Autowired
	private BCryptPasswordEncoder bc;

	@GetMapping("/alert")
	public String alert() {
		return "user/alert";
	}
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
		
		String access_token = kakaoAPI.getAccessToken(code);
		
		HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(access_token);
		System.out.println("결과:" + userInfo.toString());
		
		model.addAttribute("code",userInfo.get("nickname"));
		
		
		return "user/kakao";
	}
	
	@GetMapping("/naver")
	public String naver(@RequestParam("code") String code ,Model model)  {
		
		String access_token = naverAPI.getAccessToken(code);
		
		HashMap<String , Object> userInfo =naverAPI.getUserInfo(access_token);
		System.out.println("결과: " +userInfo.toString());
		
		model.addAttribute("naver_account",userInfo.get("naver_account"));
		model.addAttribute("name",userInfo.get("name"));
		model.addAttribute("gender",userInfo.get("gender"));
		model.addAttribute("age",userInfo.get("age"));
		model.addAttribute("mobile",userInfo.get("mobile"));
		
		Random random = new Random();
		
		int randomNumber = random.nextInt(999999);
		
		model.addAttribute("password",String.format("%06d", randomNumber));
		
		//네이버 회원가입이 안되어있으면 회원가입 , 아니면 로그인
		
		MyUserDetails vo =new MyUserDetails(userService.login(String.valueOf(userInfo.get("naver_account"))));
		
		if(vo !=null) {
			
			
			//시큐리티세션에 강제로 저장하기 (소셜로그인은 시큐리티를 타고오지않기때문에)
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(vo,null , vo.getAuthorities())  );
			
			
			return "redirect:/user/mainPage";
		}else {
			return "user/naver";
		}
		
	}
	
	@GetMapping("/find_PW")
	public String find_pw() {
		return "user/find_PW";
	}
	
	@GetMapping("/find_ID")
	public String find_id() {
		return "user/find_ID";
	}
	
	@GetMapping("/find_ID_result")
	public String find_ID_result() {
		return "user/find_ID_result";
	}
	
	@GetMapping("/code")
	public String code() {
		return "code/codeCompiler";
	}
	
}
