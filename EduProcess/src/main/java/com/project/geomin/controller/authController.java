package com.project.geomin.controller;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.geomin.command.UserVO;
import com.project.geomin.user.service.UserService;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/auth")
public class authController {

	@Autowired
	private UserService userService;
	//coolsms
	final DefaultMessageService messageService;
	
	 private Map<String, Instant> ValidCode = new HashMap<>();
	 private static final long EXPIRATION_TIME_SECONDS = 3 * 60;

	public authController(@Value("${coolsms.apikey}") String apikey , @Value("${coolsms.apisecret}") String apisecret) {
		// 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
		this.messageService =  NurigoApp.INSTANCE.initialize(apikey, apisecret, "https://api.coolsms.co.kr");
	}

	@GetMapping("/ok")
	public @ResponseBody String ok() {
		System.out.println(ValidCode);
		return "됨?";
	}

	/**
	 * 단일 메시지 발송 예제
	 * pn은 수신자번호 , auth_number는 인증번호 입력값
	 */
	@PostMapping("/send")
	public String sendOne(@RequestParam("pn") String pn,RedirectAttributes ra) {
		Message message = new Message();
		//랜덤숫자 4자리 ,현재시간
		String a = createValidCode(pn);
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
		message.setFrom("01090421075");
		message.setTo(pn);
		message.setText("인증번호\n"+a);
		System.out.println("인증번호 : " +a );
		
		
<<<<<<< HEAD
		try {
			//인증번호 보내고
			
			Thread.sleep(30000);
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
=======
>>>>>>> 7d1ef0f72f2a5b267b64ce05f19205805ffc5da7
		
		//인증번호 db에 저장하고 
		UserVO vo = userService.aLogin(pn);

		// db에 넣고 새로고침 or 찾을때마다 업데이트
		//회원정보가 있을 떄
		if(vo!= null) {
			Map<String ,Object> map = new HashMap<>();
			//아이디
			map.put("user_id",vo.getUser_id());
			//인증번호
			map.put("auth_nm",a );
			System.out.println(map);
			int an = userService.auth(map);
			if(an == 1) {
				//인증번호 업데이트 , 인증번호 받는 페이지 로 이동
				SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
				System.out.println(response);
				ra.addFlashAttribute("user_pn",pn);
				return "redirect:/auth/send_auth";
				
			}else {
				System.out.println("send에서 에러");
				 return "redirect:/auth/missAuth";
<<<<<<< HEAD
			}
		}else{
			//등록된 번호가 없다는 경고창 이후 돌아가기
			return "redirect:/auth/alertAuth";
		}
	}
	
	@PostMapping("/send_PW")
	public String send_PW(@RequestParam("pn") String pn,@RequestParam("id") String id, RedirectAttributes ra) {
		Message message = new Message();
		//랜덤숫자 4자리 ,현재시간
		String a = createValidCode(pn);
		// 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
		message.setFrom("01090421075");
		message.setTo(pn);
		message.setText("인증번호\n"+a);
		System.out.println("인증번호 : " +a );
			
		//인증번호 db에 저장하고 
		UserVO vo = userService.aLogin(pn);

		// db에 넣고 새로고침 or 찾을때마다 업데이트
		//회원정보가 있을 떄
		if(vo!= null) {
			Map<String ,Object> map = new HashMap<>();
			//아이디
			map.put("user_id",vo.getUser_id());
			//인증번호
			map.put("auth_nm",a );
			System.out.println(map);
			int an = userService.auth(map);
			if(an == 1) {
				//인증번호 업데이트 , 인증번호 받는 페이지 로 이동
				SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
				System.out.println(response);
				ra.addFlashAttribute("user_pn",pn);
				return "redirect:/auth/send_auth";
				
			}else {
				System.out.println("send에서 에러");
				 return "redirect:/auth/missAuth";
=======
>>>>>>> 7d1ef0f72f2a5b267b64ce05f19205805ffc5da7
			}
		}else{
			//등록된 번호가 없다는 경고창 이후 돌아가기
			return "redirect:/auth/alertAuth";
		}
	}

	@GetMapping("/send_auth")
	public String send_auth() {
		//인증번호 입력받고 맞으면 다음페이지
		System.out.println(ValidCode);
		return "/auth/sendAuth";
	}
	
	@PostMapping("/authCheck")
	public String authCheck(@RequestParam("auth_nm") String auth_nm, @RequestParam("user_pn") String user_pn ,RedirectAttributes ra) {
		UserVO vo =userService.authCheck(auth_nm ,user_pn);
		if(vo !=null) {
			if(verifyValidCode(user_pn)) {
<<<<<<< HEAD
				//인증완료
				ValidCode.remove(user_pn);
				if(vo.getUser_id().startsWith("@")) {
					//네이버
					return "";
				}else if(vo.getUser_id().startsWith("#")){
					//구글
					return "";
				}else {
					//기본회원
				ra.addFlashAttribute("user_id",vo.getUser_id());
				return "redirect:/user/find_ID_result";
				}
=======
				//인증완료 
				ra.addFlashAttribute("user_id",vo.getUser_id());
				return "redirect:/user/find_ID_result";
>>>>>>> 7d1ef0f72f2a5b267b64ce05f19205805ffc5da7
			}else {
				//인증실패
				System.out.println("시간넘어감");
				return "redirect:/auth/missAuth2";
			}
			
		}else {
<<<<<<< HEAD
			if(ValidCode.containsKey(user_pn)) {
				ValidCode.remove(user_pn);
			}
=======
			
>>>>>>> 7d1ef0f72f2a5b267b64ce05f19205805ffc5da7
			return "redirect:/auth/missAuth";
		}
		
	}
	
	@GetMapping("/missAuth")
	public String missAuth() {
		return "auth/missAuth";
	}
	
<<<<<<< HEAD
	@GetMapping("/alertAuth")
	public String alertAuth() {
		return "auth/alertAuth";
	}
	
=======
>>>>>>> 7d1ef0f72f2a5b267b64ce05f19205805ffc5da7
	@GetMapping("/missAuth2")
	public String missAuth2() {
		return "auth/missAuth2";
	}

	//랜덤 난수(인증번호) 생성 메소드,시간저장
	private String createValidCode(String pn) {
		Random random = new Random();
		String authenticationNumber = "";
		for (int i = 0 ; i < 4 ; i++) {
			//0~9 사이의 랜덤 수 생성
			String number = String.valueOf(random.nextInt(10));
			authenticationNumber += number;
		}
		//현재 시간 저장
		Instant currentTime = Instant.now();
		ValidCode.put(pn, currentTime);
		return authenticationNumber;
	}
	 public boolean verifyValidCode(String pn) {
	        // 저장된 시간 가져오기
	        Instant savedTime = ValidCode.get(pn);
	        if (savedTime == null) {
	        	System.out.println("저장시간없음");
	            return false; // 저장된 시간이 없으면 인증 실패
	        }
	        
	        // 현재 시간과 저장된 시간의 차이 계산 (초 단위)
	        long elapsedTimeSeconds = Instant.now().getEpochSecond() - savedTime.getEpochSecond();
	        
	        // 차이가 3분(180초) 이상이면 만료
	        return elapsedTimeSeconds <= EXPIRATION_TIME_SECONDS;
	    }

}
