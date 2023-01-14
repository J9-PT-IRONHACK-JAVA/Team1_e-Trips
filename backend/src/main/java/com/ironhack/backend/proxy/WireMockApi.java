package com.ironhack.backend.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "WireMock-API", url = "https://2jydw.wiremockapi.cloud")
public interface WireMockApi {

}
