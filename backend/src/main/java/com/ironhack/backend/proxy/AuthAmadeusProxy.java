package com.ironhack.backend.proxy;

import com.ironhack.backend.dto.amadeusFlightsDTOs.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "authAmadeus", url = "https://test.api.amadeus.com")
public interface AuthAmadeusProxy {

    @PostMapping(value = "/v1/security/oauth2/token", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AuthResponse getAccessToken(MultiValueMap <String, String> passBody);

}
