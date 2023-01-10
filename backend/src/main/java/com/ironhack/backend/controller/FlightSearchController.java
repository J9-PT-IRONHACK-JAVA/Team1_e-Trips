package com.ironhack.backend.controller;

import com.ironhack.backend.dto.FlightDTO;
import com.ironhack.backend.service.FlightApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight-search")
@RequiredArgsConstructor
public class FlightSearchController {

    private final FlightApiService flightApiService;

    @GetMapping("/inspire")
    public List<FlightDTO> getInspirationFlights(@RequestParam("origin") String origin,
                                             @RequestParam("departureDate") Optional<String> optionalDepartureDate){

        if (optionalDepartureDate.isPresent()) {
            return flightApiService.getFlightsByOriginAndDate(origin, optionalDepartureDate.get());
        } else return flightApiService.getFlightsByOrigin(origin);
    }

}
