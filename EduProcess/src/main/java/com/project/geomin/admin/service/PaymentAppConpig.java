package com.project.geomin.admin.service;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentAppConpig {
    @Value("${payment_api_key}")
    private String API_KEY;
    @Value("${payment_api_access_key}")
    private String API_SECRET;

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(API_KEY, API_SECRET);
    }

}
