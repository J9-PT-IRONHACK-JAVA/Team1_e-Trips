package com.ironhack.backend.controller;

import com.ironhack.backend.dto.FlightBookingDTO;
import com.ironhack.backend.dto.FlightDTO;
import com.ironhack.backend.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight-management")
@RequiredArgsConstructor
public class FlightManagementController {

    private final BookingRepository bookingRepository;

    @GetMapping("/user-bookings")
    public List<FlightBookingDTO> getUserSavedFlights(){
        // método esbozado... falta corregir authentication y/o login para saber cómo identificamos el current user
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FlightBookingDTO saveUserFlight(@RequestBody FlightDTO flight){
        // método esbozado... recibde flightDTO porque recibe la selección del usuario sobre una lista de flightDTO
        // me falta testarlo en postman, pero faltará tbn ver cómo se lo pasará el front.
        return null;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserFlight(@RequestParam("user") String userId, @RequestParam("flight") String flightId){
        // método esbozado, falta crear uno via postman y borrarlo desde allí
        bookingRepository.deleteById(Long.valueOf(flightId));
    }

}