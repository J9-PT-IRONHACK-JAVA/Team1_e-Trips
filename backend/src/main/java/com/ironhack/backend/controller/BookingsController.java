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
    public List<BookingDTO> getUserBookings(@PathVariable Long userId){
        var userBookings = bookingService.findAllByUser(userId);
        List<BookingDTO> userBookingsDTO = new ArrayList<>();
        for (Booking booking : userBookings){
            userBookingsDTO.add(BookingDTO.fromBooking(booking));
        }
        return userBookingsDTO;
    }

    @PostMapping("/save-flight")
    FlightBookingDTO saveFlight(@RequestParam("userId") Long userId,
                                @RequestBody FlightDTO flightDTO) {

        return bookingService.saveFlight(userId, flightDTO);
        // return FlightBookingDTO.fromFlightBooking(bookingService.saveFlight(FlightBooking.fromDTO(FlightBookingDTO.fromFlightDTO(flightDTO))));
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

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteBooking(@RequestParam("booking") String bookingId){
        //incorporar validación de ROLE_ADMIN --> (D) lo puse en scurity chain como request USER, no sé si haga falta validarlo aquí
        bookingService.delete(Long.valueOf(bookingId));
    }

}