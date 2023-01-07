package com.ironhack.taxidi.repository;

import com.ironhack.taxidi.enums.BookingType;
import com.ironhack.taxidi.model.Booking;
import com.ironhack.taxidi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findAllByBookingType(BookingType bookingType);
}
