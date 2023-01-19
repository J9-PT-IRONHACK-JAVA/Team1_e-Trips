package com.ironhack.backend.controller;

import com.ironhack.backend.dto.FlightDTO;
import com.ironhack.backend.service.BookingService;
import com.ironhack.backend.service.FlightApiService;
import com.ironhack.backend.service.HotelsApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
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

@WebMvcTest(FlightSearchController.class)
class FlightSearchControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    FlightApiService flightApiService;
    @MockBean
    BookingService bookingService;
    @MockBean
    HotelsApiService hotelsApiService;

    @Test
    @WithMockUser(username = "Admin",roles = "ADMIN")
    void getInspirationFlights() throws Exception {

        var origin ="Madrid";
        List<FlightDTO> flightDTOList = List.of(
                new FlightDTO("Madrid","Ibiza", LocalDate.now(),LocalDate.now(), BigDecimal.valueOf(50L)),
                new FlightDTO("Madrid","Berlin", LocalDate.now(),LocalDate.now(), BigDecimal.valueOf(50L))
        );

        when(flightApiService.getFlightsByOrigin(origin)).thenReturn(flightDTOList);

        mockMvc.perform(get("/flight-search/inspire")
                        .param("origin","Madrid"))
                //.with(httpBasic("admin","admin")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)));

    }


}
