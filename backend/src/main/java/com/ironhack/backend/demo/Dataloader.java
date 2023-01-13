package com.ironhack.backend.demo;

import com.ironhack.backend.enums.BookingType;
import com.ironhack.backend.model.Booking;
import com.ironhack.backend.model.FlightBooking;
import com.ironhack.backend.model.HotelBooking;
import com.ironhack.backend.model.User;
import com.ironhack.backend.repository.BookingRepository;
import com.ironhack.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@Component
@RequiredArgsConstructor
@Log
@Profile("demo")
public class Dataloader {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void storeUser(){
        log.info("Starting demo user loading...");
        User user1 = new User("user", passwordEncoder.encode("user"), "user@user.com","ROLE_USER");

        userRepository.save(user1);
        log.info("User " + user1.getUsername() + " was created successfully");

        User user2 = new User("admin", passwordEncoder.encode("admin"),"admin@admin.com", "ROLE_USER,ROLE_ADMIN");
        userRepository.save(user2);
        log.info("User " + user2.getUsername() + " was created successfully");
        log.info("Finished demo user loading.");

        Booking flightBooking1 = new FlightBooking(1L, BookingType.FLIGHT, user1, LocalDate.of(2023, 9,28), LocalDate.of(2023, 9,28), "BCN", "MAD", "VY2340", "Vueling", 1, new BigDecimal(56.99));
        bookingRepository.save(flightBooking1);

        Booking hotelBooking1 = new HotelBooking(2L, BookingType.HOTEL, user2, LocalDate.of(2023, 7, 15), LocalDate.of(2023, 7, 17), "Hilton", 1, new BigDecimal(78.89));
        bookingRepository.save(hotelBooking1);

    }

}
