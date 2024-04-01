package com.project.geomin.user.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;



@Component("kakao")
public class KakaoAPI {

	public String getAccessToken(String code) {
		String access_token = "";
		String refresh_token = "";
		String requestURL = "https://kauth.kakao.com/oauth/token"; //요청주소

		try {
			URL url = new URL(requestURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();

			//    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			//    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			
			String req = "grant_type=authorization_code"
					   + "&client_id=5b36e517cda6ce71a97d292f2d6e5388" //본인키
					   + "&redirect_uri=http://127.0.0.1:8989/user/kakao"
					   + "&code=" + code;
			bw.write(req);
			bw.flush();
			
			System.out.println("요청결과:" + conn.getResponseCode());
			
			if(conn.getResponseCode() == 200 ) {//성공
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	               
	            String result = "";
	            String str;
	            while( (str = br.readLine()) != null ) {
	            	result += str;
	            }
	            
	            /*
				GSON vs org.json.JSONObject
				1) GSON은 Object 정의를 사용하여 원하는 유형의 객체를 직접 생성할 수 있습니다. JSONObject는 수동으로 구문 분석해야 합니다.
				2) org.json은 간단한 트리 스타일 API입니다. 가장 큰 약점은 구문 분석하기 전에 전체 JSON 문서를 문자열로 로드해야 한다는 것입니다. 큰 JSON 문서의 경우 이는 비효율적일 수 있습니다.
				3) 지금까지 org.json 구현의 가장 큰 약점은 JSONException입니다. 모든 JSON 항목 주위에 try/catch 블록을 배치해야 하는 것은 편리하지 않습니다.
				4) Gson은 Android에서 JSON 구문 분석을 위한 최고의 API입니다. 매우 작은 바이너리 크기(200KiB 미만)를 가지며 빠른 데이터 바인딩을 수행하며 사용하기 쉬운 간단한 API를 제공합니다.
				5) GSON과 Jackson은 자바 세계에서 JSON 데이터를 관리하기 위한 가장 대중적인 솔루션입니다.
				*/
	            
        //gson파서를 통한 역분해
        //엘리먼트(모형) -> 오브젝트 -> 문자열
        JsonParser json = new JsonParser();
        JsonElement element = json.parse(result);
        JsonObject obj = element.getAsJsonObject();
        access_token = obj.get("access_token").getAsString();
        refresh_token = obj.get("refresh_token").getAsString();
        
        //System.out.println(access_token);
        //System.out.println(refresh_token);
	            
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	}
	
	public HashMap<String, Object> getUserInfo(String access_token) {
		
		 //요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
	    HashMap<String, Object> map = new HashMap<>();
	    String requestURL = "https://kapi.kakao.com/v2/user/me";
	    try {
	        URL url = new URL(requestURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setDoOutput(true);
	        //요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + access_token);

	        //문서에 파라미터 값은 필요가 없다라고 명시됨

			System.out.println("요청결과:" + conn.getResponseCode());
			
			if(conn.getResponseCode() == 200) {
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
		        String line = "";
		        String result = "";
		        while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        
//		        System.out.println("kakaoAPI 결과 : "+result);
		        //GSON역분해
		        //카카오디벨로퍼에서 - property_keys확인
		        JsonParser json = new JsonParser();
		        JsonElement element = json.parse(result);
		        
//		        System.out.println("JSONElement parse : " + element);
		        
		        //json에서 key를 꺼내고, 다시 key를 꺼낸다.
		        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
		        
		        System.out.println("properties : " +properties);
		        
		        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
		        
		        System.out.println("account : " +kakao_account);
		        
		        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
		        
//		        System.out.println("nickname : " +nickname);
		        
//		        String email = kakao_account.getAsJsonObject().get("email").getAsString();
		        
//		        System.out.println("email : " +email);
		        map.put("nickname", nickname); //필수동의
//		        map.put("email", email); //선택동의
		        
			}
	        

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return map;
	}
	
	
}