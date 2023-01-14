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
import java.util.ArrayList;
import java.util.List;

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

        Booking hotelBooking1 = new HotelBooking(2L, BookingType.HOTEL, user2, LocalDate.of(2023, 7, 15), LocalDate.of(2023, 7, 17), "Hilton", 1, new BigDecimal(78.89));
        bookingRepository.save(hotelBooking1);

        var mockedFlightList = new ArrayList<>(List.of(
                new FlightBooking(1L, BookingType.FLIGHT, user1, LocalDate.parse("2023-01-27"), LocalDate.parse("2023-01-29"), "BCN", "MAD", "VY2340", "Vueling", 1, new BigDecimal(56.99)),
                new FlightBooking(2L, BookingType.FLIGHT, user1, LocalDate.parse("2023-02-10"), LocalDate.parse("2023-02-13"), "BCN", "LON", "VY2340", "Vueling", 1, new BigDecimal(128.50)),
                new FlightBooking(3L, BookingType.FLIGHT, user1, LocalDate.parse("2023-02-18"), LocalDate.parse("2023-02-22"), "LON", "NYC", "VY2340", "Vueling", 1, new BigDecimal(750.25)),
                new FlightBooking(4L, BookingType.FLIGHT, user1, LocalDate.parse("2023-03-06"), LocalDate.parse("2023-03-06"), "NYC", "BOS", "VY2340", "Vueling", 1, new BigDecimal(326.00))
        ));
        for(FlightBooking flightBooking : mockedFlightList){
            bookingRepository.save(flightBooking);
        }

        var mockedHotelsList = new ArrayList<>(List.of(
                new HotelBooking(5L, BookingType.HOTEL, user1, LocalDate.parse("2023-01-27"), LocalDate.parse("2023-01-29"), "Hilton Diagonal", 1, new BigDecimal(78.89)),
                new HotelBooking(6L, BookingType.HOTEL, user1, LocalDate.parse("2023-02-10"), LocalDate.parse("2023-02-13"), "Piccadilly Regency", 2, new BigDecimal(970.10)),
                new HotelBooking(7L, BookingType.HOTEL, user1, LocalDate.parse("2023-02-18"), LocalDate.parse("2023-02-22"), "Hyatt Central Park", 2, new BigDecimal(564.55))
                ));
        for(HotelBooking hotelBooking : mockedHotelsList){
            bookingRepository.save(hotelBooking);
        }

    }

}
