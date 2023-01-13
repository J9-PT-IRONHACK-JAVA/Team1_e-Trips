package com.ironhack.backend.controller;

import com.ironhack.backend.service.BookingService;
import com.ironhack.backend.service.FlightApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(FlightSearchController.class)
class FlightSearchControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    FlightApiService flightApiService;
    @MockBean
    BookingService bookingService;

    @Test
    void getInspirationFlights() {
    }

    @Test
    void saveFlight() {
    }
}