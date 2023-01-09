package com.ironhack.backend.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class AmadeusFeignClientConfiguration {

    @Value("${amadeus.oauth.accessToken}")
    private String amadeusOauthAccessToken;

    @Bean
    public RequestInterceptor bearerTokenRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header("Authorization",
                        String.format("Bearer %s", amadeusOauthAccessToken));
            }
        };
    }

}
