package com.ironhack.backend.service;

import com.ironhack.backend.dto.BookingDTO;
import com.ironhack.backend.model.Booking;
import com.ironhack.backend.model.FlightBooking;
import com.ironhack.backend.model.HotelBooking;
import com.ironhack.backend.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public List<Booking> findAllByUser(Long id) {
        return bookingRepository.findAllByUserUserId(id);
    }

    public HotelBooking saveHotel(HotelBooking hotelbooking) {
        return bookingRepository.save(hotelbooking);
    }

    public FlightBooking saveFlight(FlightBooking flightbooking) {
        return bookingRepository.save(flightbooking);
    }

    public List<Booking> saveAll(List<Booking> listOfBookings) {
        return bookingRepository.saveAll(listOfBookings);
    }

    public void delete(Long id){  bookingRepository.deleteById(id);}


    public BookingDTO updateBooking(Long bookingId, Optional<String> name, Optional<Integer> travelers, Optional<String> purpose) {
        //TODO: hacer el patch (SET) de estas 3 properties
        return null; // BookingDTO.fromBooking(...)
    }
}
