package com.ironhack.taxidi.controller;

import com.ironhack.taxidi.dto.FlightDTO;
import com.ironhack.taxidi.service.FlightApiService;
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
    public List<FlightDTO> getInspireflights(@RequestParam("origin") Optional<String> optionalOrigin,
                                             @RequestParam("departureDate") Optional<String> optionalDepartureDate){

        if (optionalOrigin.isPresent() && optionalDepartureDate.isEmpty()){
            return flightApiService.getFlightsOrigin(optionalOrigin);
        }

        return flightApiService.getFlightsOriginDate(optionalOrigin, optionalDepartureDate);
    }


}
