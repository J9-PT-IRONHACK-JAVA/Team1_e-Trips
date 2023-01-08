package com.ironhack.backend.service;


import com.ironhack.backend.model.Booking;
import com.ironhack.backend.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> saveAll(List<Booking> listOfBookings) {
        return bookingRepository.saveAll(listOfBookings);
    }

}
