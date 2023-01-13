package com.ironhack.backend.controller;

import com.ironhack.backend.dto.HotelBookingDTO;
import com.ironhack.backend.dto.HotelDTO;
import com.ironhack.backend.model.HotelBooking;
import com.ironhack.backend.repository.BookingRepository;
import com.ironhack.backend.service.BookingService;
import com.ironhack.backend.service.HotelsApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel-search")
@RequiredArgsConstructor
public class HotelSearchController {
    private final HotelsApiService hotelsApiService;
    private final BookingRepository bookingRepository;
    private final BookingService bookingService;

    @GetMapping
    List<HotelDTO> findHotelOffers(@RequestParam("cityCode") String cityCode,
                                   @RequestParam("checkInDate") String checkInDate,
                                   @RequestParam("checkOutDate") String checkOutDate,
                                   @RequestParam("guests") Integer guests) {

        return hotelsApiService.getHotelOffers(cityCode, checkInDate, checkOutDate, guests);
    }


}
