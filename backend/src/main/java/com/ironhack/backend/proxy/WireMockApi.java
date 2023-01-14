package com.ironhack.backend.proxy;

import com.ironhack.backend.dto.amadeusFlightsDTOs.InspirationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "WireMock-API", url = "https://2jydw.wiremockapi.cloud")
public interface WireMockApi {

    @GetMapping("/flight-search/inspire")
    InspirationResponse getInspirationFlights(@RequestParam("origin") String origin, @RequestParam("departureDate") String departureDate);

}
