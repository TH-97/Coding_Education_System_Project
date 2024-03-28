package com.project.geomin.user.jwt.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class JWTService {

	private static String secretKey ="choongang";
	
	//토큰 생성
	public static String createToken(String username) {
		
		Algorithm al = Algorithm.HMAC256(secretKey);
		
		//만료시간
		long expire = System.currentTimeMillis()+360000*24;
		
		Builder builder = JWT.create()
				.withSubject(username)//토큰의주제
				.withIssuedAt(new Date())//토큰 발급 시간
				.withExpiresAt(new Date(expire)) //만료시간
				.withIssuer("임혁진") //토큰발행자
				.withClaim("임혁진", "관리자입니다");
		
		return builder.sign(al); //비밀키로 서명하여 토큰 생성
	}
	//검증
	public static boolean validateToken(String token) throws JWTVerificationException{
		Algorithm al =Algorithm.HMAC256(secretKey);
		JWTVerifier verifier = JWT.require(al).build(); //토큰 검증 객체생성
		
		verifier.verify(token);
		return true; 
	}
}
