package com.project.geomin.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketSTOMPConfig implements WebSocketMessageBrokerConfigurer{
	


	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat")
        .setAllowedOriginPatterns("*")
        .withSockJS();
		
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/pub"); //클라이언트가 서버에게 보낼주소 //pub이 붙어있으면 broker로 보내짐
		
		
		 registry.enableSimpleBroker("/topic","/queue"); //서버가 구독한 클라이언트한테 보낼 주소 //sub 구독자
		//경로 맨앞에 붙은경우, broker가 잡아서 구독자에게 메시지를 뿌린다
	}

	

}
