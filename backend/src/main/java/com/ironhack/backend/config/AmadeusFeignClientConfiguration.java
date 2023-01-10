package com.ironhack.backend.config;

import com.ironhack.backend.proxy.AuthAmadeusProxy;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
@RequiredArgsConstructor
public class AmadeusFeignClientConfiguration {

    @Value("${amadeus.oauth.client_id}")
    private String amadeusClientId;
    @Value("${amadeus.oauth.client_secret}")
    private String amadeusClientSecret;

    private final AuthAmadeusProxy authAmadeusProxy;

    public String generateToken() {
        MultiValueMap<String, String> authBody = new LinkedMultiValueMap<>();
        authBody.add("grant_type", "client_credentials");
        authBody.add("client_id", amadeusClientId);
        authBody.add("client_secret", amadeusClientSecret);

        return authAmadeusProxy.getAccessToken(authBody).getAccess_token();
    }

    @Bean
    public RequestInterceptor bearerTokenRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header("Authorization",
                        String.format("Bearer %s", generateToken()));
            }
        };
    }

}
