package com.ironhack.backend.controller;

import com.ironhack.backend.dto.FlightBookingDTO;
import com.ironhack.backend.dto.FlightDTO;
import com.ironhack.backend.dto.HotelBookingDTO;
import com.ironhack.backend.dto.HotelDTO;
import com.ironhack.backend.model.FlightBooking;
import com.ironhack.backend.model.HotelBooking;
import com.ironhack.backend.service.BookingService;
import com.ironhack.backend.service.FlightApiService;
import com.ironhack.backend.service.HotelsApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight-search")
@RequiredArgsConstructor
public class FlightSearchController {

    private final FlightApiService flightApiService;
    private final BookingService bookingService;

    @GetMapping("/inspire")
    public List<FlightDTO> getInspirationFlights(@RequestParam("origin") String origin,
                                             @RequestParam("departureDate") Optional<String> optionalDepartureDate){

        if (optionalDepartureDate.isPresent()) {
            return flightApiService.getFlightsByOriginAndDate(origin, optionalDepartureDate.get());
        } else return flightApiService.getFlightsByOrigin(origin);
    }

}
