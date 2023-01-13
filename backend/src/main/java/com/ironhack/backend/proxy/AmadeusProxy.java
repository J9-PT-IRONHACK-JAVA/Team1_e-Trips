package com.ironhack.backend.proxy;

import com.ironhack.backend.config.AmadeusFeignClientConfiguration;
import com.ironhack.backend.dto.amadeusFlightsDTOs.HotelAvailabilityResponse;
import com.ironhack.backend.dto.amadeusFlightsDTOs.HotelsByCityResponse;
import com.ironhack.backend.dto.amadeusFlightsDTOs.InspirationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Amadeus-API", url = "https://test.api.amadeus.com", configuration = AmadeusFeignClientConfiguration.class)
public interface AmadeusProxy {

    @GetMapping("/v1/shopping/flight-destinations")
    InspirationResponse getInspirationFlights(@RequestParam("origin") String origin, @RequestParam("departureDate") String departureDate);
    @GetMapping("/v1/shopping/flight-destinations")
    InspirationResponse getInspirationFlightsOnlyOrigin(@RequestParam("origin") String origin);

    @GetMapping("/v1/reference-data/locations/hotels/by-city")
    HotelsByCityResponse getHotelsByCityAndRating(@RequestParam("cityCode") String cityCode, @RequestParam("ratings") Integer[] stars);
    @GetMapping("v3/shopping/hotel-offers")
    HotelAvailabilityResponse getAvailability(@RequestParam("hotelIds") String[] hotelIdArray,
                                              @RequestParam("adults") Integer adults,
                                              @RequestParam("checkInDate") String checkInDate,
                                              @RequestParam("checkOutDate") String checkOutDate);


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
