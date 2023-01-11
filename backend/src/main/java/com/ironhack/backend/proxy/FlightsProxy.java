package com.ironhack.backend.proxy;

import com.ironhack.backend.config.AmadeusFeignClientConfiguration;
import com.ironhack.backend.dto.amadeusFlightsDTOs.InspirationResponse;
import com.nimbusds.oauth2.sdk.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "flightsAmadeus", url = "https://test.api.amadeus.com", configuration = AmadeusFeignClientConfiguration.class)
public interface FlightsProxy {

    @GetMapping("/v1/shopping/flight-destinations")
    InspirationResponse getInspirationFlights(@RequestParam("origin") String origin, @RequestParam("departureDate") String departureDate);
    @GetMapping("/v1/shopping/flight-destinations")
    InspirationResponse getInspirationFlightsOnlyOrigin(@RequestParam("origin") String origin);


/*
    [PT]: Esta de momento la dejo sin desarrollar, porque la respuesta es bastante compleja... ya la añadiremos si tenemos tiempo!

    @GetMapping("/v2/shopping/flight-offers")
    FlightOffersResponse getFlightOffers(@RequestParam("originLocationCode") String origin,
                                         @RequestParam("destinationLocationCode") String destination,
                                         @RequestParam("departureDate") String departureDate,
                                         @RequestParam("returnDate") String returnDate,
                                         @RequestParam("adults") Integer adults,
                                         @RequestParam("max") Integer maxResults);
*/

}
