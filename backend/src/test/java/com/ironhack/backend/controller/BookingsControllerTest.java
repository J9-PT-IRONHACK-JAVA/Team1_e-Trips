package com.ironhack.backend.controller;
import com.ironhack.backend.dto.BookingDTO;
import com.ironhack.backend.enums.BookingType;
import com.ironhack.backend.model.Booking;
import com.ironhack.backend.model.FlightBooking;
import com.ironhack.backend.model.HotelBooking;
import com.ironhack.backend.model.User;
import com.ironhack.backend.service.BookingService;
import com.ironhack.backend.service.FlightApiService;
import com.ironhack.backend.service.HotelsApiService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingsController.class)
class BookingsControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    BookingService bookingService;

    @MockBean
    FlightApiService flightApiService;
    @MockBean
    HotelsApiService hotelsApiService;



    @Test
    @WithMockUser(username = "Admin",roles = "ADMIN")
    public void getUserBookings() throws Exception {
        User user1 = new User("user", "user", "user@user.com","ROLE_USER");
        User user2 = new User("admin", "admin","admin@admin.com", "ROLE_USER,ROLE_ADMIN");

        List<Booking> bookingList = List.of(
                new Booking(1L, BookingType.HOTEL,user2, BigDecimal.valueOf(10)),
                new Booking(2L, BookingType.FLIGHT,user2, BigDecimal.valueOf(20)));

        var id = 1L;
        when(bookingService.findAllByUser(id)).thenReturn(bookingList);

        mockMvc.perform(get("/my-bookings/{id}",id))
                        //.with(httpBasic("admin","admin")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)));
    }

    @Test
    @WithMockUser(username = "Admin",roles = "ADMIN")
    public void updateBooking() throws Exception {

        var bookingId=1L;
        var name = "Paquito";
        var travelers = 2;
        var purpose = "Holiday";
        var responseDto = new BookingDTO(1L,BookingType.FLIGHT,BigDecimal.valueOf(50L),name,purpose,2);
        when(bookingService.updateBooking(bookingId, Optional.of(name), Optional.of(travelers), Optional.of(purpose))).thenReturn(responseDto);
        mockMvc.perform(patch("/my-bookings/{bookingId}",bookingId)
                        .param("name",name)
                        .param("travelers", String.valueOf(travelers))
                        .param("purpose",purpose))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Paquito"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.purpose").value("Holiday"));
    }

    @Test
    @WithMockUser(username = "Admin",roles = "ADMIN")
    public void deleteBooking() throws Exception {
        var testId = 1L;
        doNothing().when(bookingService).delete(testId);
        mockMvc.perform(delete("/my-bookings")
                        //.param("role","ADMIN")
                        .param("booking", String.valueOf(testId)))
                .andDo(print())
                //.andExpect(status().isOk())
                .andExpect(status().isNoContent());
    }
}