package com.ironhack.backend.config;


import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@RequiredArgsConstructor

public class HotelBedsConfiguration {

    @Value("${hotel_beds.api_keys.key}")
    private String hotelBedsApiKey;
    @Value("${hotel_beds.api_keys.secret}")
    private String hotelBedsSecret;

    public String generateSignature(){

        //UTC creation
        var utcDate = Math.floor(new Date().getTime() / 1000);

        var assemble = (hotelBedsApiKey+hotelBedsSecret+utcDate);

        return DigestUtils.sha256Hex(assemble);
    }


    @Bean
    public RequestInterceptor headerRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json"); //only for Availability API. If the others crash, remove this and pass it as @RequestHeader
            requestTemplate.header("Accept", "application/json");
            requestTemplate.header("Accept-Encoding", "gzip");
            requestTemplate.header("Api-key", hotelBedsApiKey);
            requestTemplate.header("X-Signature", generateSignature());
        };
    }

}
