package com.ironhack.backend.controller;

import com.ironhack.backend.dto.FlightDTO;
import com.ironhack.backend.dto.HotelDTO;
import com.ironhack.backend.repository.BookingRepository;
import com.ironhack.backend.service.BookingService;
import com.ironhack.backend.service.HotelsApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
    void findHotelOffers() throws Exception {

        var cityCode ="MAD";
        var checkInDate ="2023-01-01";
        var checkOutDate ="2024-01-01";
        int guests =2;

        List<HotelDTO> hotelDTOList = List.of(
                new HotelDTO("cocoloco", LocalDate.now(), LocalDate.now(),2,BigDecimal.valueOf(50L)),
                new HotelDTO("locococo", LocalDate.now(), LocalDate.now(),2,BigDecimal.valueOf(51L)));


        when(hotelsApiService.getHotelOffers(cityCode,checkInDate,checkOutDate,guests)).thenReturn(hotelDTOList);

        mockMvc.perform(get("/hotel-search")
                        .param("cityCode",cityCode)
                        .param("checkInDate",checkInDate)
                        .param("checkOutDate",checkOutDate)
                        .param("guests", String.valueOf(guests))
                        .with(httpBasic("admin","admin")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)));


    }

}