package com.project.geomin.user.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component("naver")
public class NaverAPI {

	public String getAccessToken(String code){
		String clientId = "hGxkco0a05QxwVjiglkx";//애플리케이션 클라이언트 아이디값";
		String clientSecret = "xWwaEnVpSL";//애플리케이션 클라이언트 시크릿값";
		String redirectURI = "http://127.0.0.1:8989/user/naver";
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + "STATE_STRING";
		String access_token = "";
		String refresh_token = "";
		System.out.println("apiURL="+apiURL);
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.println("responseCode="+responseCode);
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			String res ="";
			while ((inputLine = br.readLine()) != null) {
				res += inputLine;
			}
			br.close();
			if(responseCode==200) {
				System.out.println("res.tostring : " + res.toString());
			}
			JsonParser json = new JsonParser();
	        JsonElement element = json.parse(res);
	        JsonObject obj = element.getAsJsonObject();
	        access_token = obj.get("access_token").getAsString();
	        refresh_token = obj.get("refresh_token").getAsString();
		} catch (Exception e) {
			System.out.println("NaverAPI : " +e);
		}
		return access_token;
	}
	public HashMap<String, Object> getUserInfo(String access_token) {

		//요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> map = new HashMap<>();
		String requestURL = "https://openapi.naver.com/v1/nid/me";
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

				String line;
				String result = "";
				while ((line = br.readLine()) != null) {
					result += line;
				}

				//GSON역분해
				//카카오디벨로퍼에서 - property_keys확인
				JsonParser json = new JsonParser();
				JsonElement element = json.parse(result);

				//json에서 key를 꺼내고, 다시 key를 꺼낸다.
				JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();

				String naver_account = response.getAsJsonObject().get("id").getAsString();
				String gender = response.getAsJsonObject().get("gender").getAsString();
				String name = response.getAsJsonObject().get("name").getAsString();
				String mobile = response.getAsJsonObject().get("mobile").getAsString();
				int age = response.getAsJsonObject().get("birthyear").getAsInt();

				map.put("naver_account", naver_account); 
				map.put("gender",gender);
				map.put("mobile", mobile);
				map.put("name", name);
				map.put("age", age);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
}
