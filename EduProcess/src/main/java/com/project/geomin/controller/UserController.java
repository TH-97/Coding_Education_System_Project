package com.project.geomin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.project.geomin.admin.service.AdminService;
import com.project.geomin.command.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.geomin.command.UserVO;
import com.project.geomin.user.security.MyUserDetails;
import com.project.geomin.user.service.KakaoAPI;
import com.project.geomin.user.service.NaverAPI;
import com.project.geomin.user.service.UserMapper;
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

	@Autowired
	@Qualifier("AdminService")
	private AdminService adminService;
	
//	private MyUserDetails myUserDetails;

	@Autowired
	private BCryptPasswordEncoder bc;

	@GetMapping("/alert")
	public String alert() {
		return "user/alert";
	}
	
	@GetMapping("/join")
	public String stud_join() {
		return "user/join";
	}
	@GetMapping("/test")
	public String test() {
		return "code/test1";
	}
	@GetMapping("/editor")
	public String editor() {
		return "code/editor";
	}
	

	@PostMapping("/register")
	public String register(UserVO userVO,Model model) {

		String pw = bc.encode(userVO.getUser_pw());
		userVO.setUser_pw(pw);
		System.out.println(userVO.getUser_id());
		if(userService.aLogin(userVO.getUser_pn()) !=null){
			model.addAttribute("message","이미 가입된 전화번호가 있습니다");
			return "user/main_page";
		}else if(userService.checkLogin(userVO.getUser_id()) != null) {
			model.addAttribute("message","중복된 아이디 입니다");
			return "user/main_page";
		}
		
		int a = userService.join(userVO);

		if(a==1) {
			System.out.println(userVO.getUser_id());
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
			model.addAttribute("message" , "아이디와 비밀번호를 확인해주세요");
		}


		return "user/main_page";

	}

	@GetMapping("/mainPage")
	public String mainPage(Model model){
		List<AdminVO> list = adminService.getContent();
		model.addAttribute("list",list);
		return "user/main_page";
	}
	@GetMapping("/mainPage2")
	public String mainPage2(Model model){
		List<AdminVO> list = adminService.getContent();
		model.addAttribute("list",list);
		return "user/main_page2";
	}
	
	@GetMapping("/kakao")
	public String kakao(@RequestParam("code") String code,Model model) {
		
		String access_token = kakaoAPI.getAccessToken(code);
		
		HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(access_token);
		System.out.println("결과:" + userInfo.toString());
		
		model.addAttribute("code",userInfo.get("nickname"));
		
		
		return "user/kakao";
	}
	
	@PostMapping("/googleJoin")
	public String googleJoin(UserVO vo,Model model){
		
		System.out.println("구글vo조인 : " + vo);
		vo.setRole("ROLE_stud");
		if(userService.aLogin(vo.getUser_pn()) !=null){
			model.addAttribute("message","이미 가입된 전화번호가 있습니다");
			return "user/main_page";
		}
		int a = userService.join(vo);

		if(a==1) {
			return "user/success";
		}else {
			return "user/alert";

		}
	}
	
	@GetMapping("/naver")
	public String naver(@RequestParam("code") String code ,Model model)  {
		
		String access_token = naverAPI.getAccessToken(code);
		
		HashMap<String , Object> userInfo =naverAPI.getUserInfo(access_token);
		System.out.println("결과: " +userInfo.toString());
		
		//네이버 회원가입이 안되어있으면 회원가입 , 아니면 로그인
		String id = "@"+String.valueOf(userInfo.get("naver_account"));
		System.out.println("naver아이디 : " +id);
		UserVO vo = userService.login(id);
		if(vo ==null) {
			
			vo = UserVO.builder()
				 .user_id("@"+String.valueOf(userInfo.get("naver_account")))
				 .user_age(Integer.valueOf(String.valueOf(userInfo.get("age"))))
				 .user_gn(String.valueOf(userInfo.get("gender")))
				 .role("ROLE_stud")
				 .user_nm(String.valueOf(userInfo.get("name")))
				 .user_pn(String.valueOf(userInfo.get("mobile")))
				 .build();
				 System.out.println(vo);
			if(userService.aLogin(String.valueOf(userInfo.get("mobile"))) !=null){
				//이미 가입된 전화번호가 있다.
				model.addAttribute("message","이미 가입된 전화번호가 있습니다");
				return "user/main_page";
			}
			int a = userService.join(vo);
			if(a ==1) {
			return "user/success";
			}else {
				return "user/alert";
			}
		}
		MyUserDetails vo1 =new MyUserDetails(vo);

		
		SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken(vo1, null, vo1.getAuthorities()));
		return "redirect:/user/mainPage";
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
	
	@GetMapping("/find_PW_result")
	public String find_PW_result() {
		return "user/find_PW_result";
	}
	
	@GetMapping("/code")
	public String code() {
		return "code/codeCompiler";
	}
	
	@PostMapping("/reset_pw")
	public String reset_pw(@RequestParam("pn") String pn , @RequestParam("pw") String pw , Model model) {
		String bcPw= bc.encode(pw);
		userService.updatePW(pn,bcPw);
		model.addAttribute("message","비밀번호가 재설정 되었습니다. 다시 로그인 하여 주십시오");
		//메시지 담고 메인페이지로
		return "redirect:/user/mainPage";
	}
	
	@GetMapping("/homeWork")
	public String homeWork() {
		return "code/homeWork";
	}
	
	@GetMapping("/GoogleJoin")
	public String GoogleJoin() {
		return "user/GoogleJoin";
	}
	
}
