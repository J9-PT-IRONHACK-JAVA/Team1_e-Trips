package com.ironhack.backend.controller;

import com.ironhack.backend.dto.BookingDTO;
import com.ironhack.backend.model.Booking;
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

    @GetMapping("/{id}")
    public List<BookingDTO> getUserBookings(@PathVariable Long id){
        var userBookings = bookingService.findAllByUser(id);
        List<BookingDTO> userBookingsDTO = new ArrayList<>();
        for (Booking booking : userBookings){
            userBookingsDTO.add(BookingDTO.fromBooking(booking));
        }
        return userBookingsDTO;
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
    public void deleteBooking(@RequestParam("role") String userRole, @RequestParam("booking") String bookingId){
        //incorporar validación de ROLE_ADMIN
        bookingService.delete(Long.valueOf(bookingId));
    }

}