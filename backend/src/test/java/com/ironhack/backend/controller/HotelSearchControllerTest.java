package com.ironhack.backend.controller;

import com.ironhack.backend.repository.BookingRepository;
import com.ironhack.backend.service.BookingService;
import com.ironhack.backend.service.HotelsApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

class HotelSearchControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    HotelsApiService hotelsApiService;
    @MockBean
    BookingRepository bookingRepository;
    @MockBean
    BookingService bookingService;

    @Test
    void findHotelOffers() {
    }

    @Test
    void saveHotel() {
    }
}