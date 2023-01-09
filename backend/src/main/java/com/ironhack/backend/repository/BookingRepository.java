package com.ironhack.backend.repository;

import com.ironhack.backend.enums.BookingType;
import com.ironhack.backend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findAllByBookingType(BookingType bookingType);
}
