package com.ironhack.backend.controller;

import com.ironhack.backend.dto.*;
import com.ironhack.backend.model.Booking;
import com.ironhack.backend.model.FlightBooking;
import com.ironhack.backend.model.HotelBooking;
import com.ironhack.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/my-bookings")
@RequiredArgsConstructor
public class BookingsController {

    private final BookingService bookingService;

    @GetMapping("/{userId}")
    public MyBookingsDTO getUserBookings(@PathVariable Long userId){
        return bookingService.findAllByUser(userId);
    }

    @PostMapping("/save-flight")
    FlightBookingDTO saveFlight(@RequestParam("userId") Long userId,
                                @RequestBody FlightDTO flightDTO) {

        return bookingService.saveFlight(userId, flightDTO);
    }

    @PostMapping("/save-hotel")

    HotelBookingDTO saveHotel(@RequestParam("userId") Long userId,@RequestBody HotelDTO hotelDTO) {
        return bookingService.saveHotel(userId, hotelDTO);
    }

    @PatchMapping("/{bookingId}")
    public BookingDTO updateBooking(@PathVariable Long bookingId,
                                     @RequestParam Optional<String> name,
                                     @RequestParam Optional<Integer> travelers,
                                     @RequestParam Optional<String> purpose){
        return bookingService.updateBooking(bookingId, name, travelers, purpose);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBooking(@PathVariable(name = "id") Long bookingId){
        bookingService.delete(bookingId);
    }

}