package com.ironhack.backend.controller;

import com.ironhack.backend.dto.UserDTO;
import com.ironhack.backend.enums.BookingType;
import com.ironhack.backend.model.Booking;
import com.ironhack.backend.model.User;
import com.ironhack.backend.security.JpaUserDetailsService;
import com.ironhack.backend.security.SecurityConfig;
import com.ironhack.backend.security.SecurityUser;
import com.ironhack.backend.service.FlightApiService;
import com.ironhack.backend.service.HotelsApiService;
import com.ironhack.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ManageUsersController.class)
@Import({JpaUserDetailsService.class, SecurityUser.class, SecurityConfig.class})
class ManageUsersControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    @MockBean
    FlightApiService flightApiService;
    @MockBean
    HotelsApiService hotelsApiService;



    @Test
    void getAllUsers() throws Exception {


        List<UserDTO> userDTOList = List.of(
                new UserDTO(),
                new UserDTO(),
                new UserDTO());

        when(userService.findAll()).thenReturn(userDTOList);

        mockMvc.perform(get("/user-management/users")
                        .with(httpBasic("admin","admin")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(3)));
    }

    @Test
    void createUser() {


    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}