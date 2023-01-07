package com.ironhack.taxidi.demo;

import com.ironhack.taxidi.enums.BookingType;
import com.ironhack.taxidi.model.Booking;
import com.ironhack.taxidi.model.FlightBooking;
import com.ironhack.taxidi.model.HotelBooking;
import com.ironhack.taxidi.model.User;
import com.ironhack.taxidi.repository.BookingRepository;
import com.ironhack.taxidi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

        Booking flightBooking1 = new FlightBooking(1L, BookingType.FLIGHT, user1,"2023-11-01T11:35:00", "2023-11-01T12:35:00", "BCN", "MAD", "VY2340", "Vueling", 1);
        bookingRepository.save(flightBooking1);

        Booking hotelBooking1 = new HotelBooking(2L, BookingType.HOTEL, user2, "2023-11-01T11:35:00", "2023-11-05T11:35:00", "Diagonal 23", "Hilton", 1);
        bookingRepository.save(hotelBooking1);

    }

}