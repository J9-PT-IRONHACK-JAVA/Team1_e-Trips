package com.ironhack.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.backend.dto.UserDTO;
import com.ironhack.backend.enums.BookingType;
import com.ironhack.backend.model.Booking;
import com.ironhack.backend.model.User;
import com.ironhack.backend.repository.UserRepository;
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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ManageUsersController.class)
//@Import({JpaUserDetailsService.class, SecurityUser.class, SecurityConfig.class})
class ManageUsersControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    @MockBean
    FlightApiService flightApiService;
    @MockBean
    HotelsApiService hotelsApiService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    JpaUserDetailsService jpaUserDetailsService;
    @Autowired
    ObjectMapper om;



    @Test
    @WithMockUser(username = "Admin",roles = "ADMIN")
    void getAllUsers() throws Exception {


        List<UserDTO> userDTOList = List.of(
                new UserDTO(),
                new UserDTO(),
                new UserDTO());

        when(userService.findAll()).thenReturn(userDTOList);
        //when(jpaUserDetailsService.loadUserByUsername()).thenReturn(userDTOList);

        mockMvc.perform(get("/user-management/users"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(3)));
    }

    @Test
    @WithMockUser(username = "Admin",roles = "ADMIN")
    void createUser() throws Exception {

        UserDTO userDTO = new UserDTO("Iñaki","zdbhzh","iñaki@ironhack.com");
        userDTO.setRoles("ADMIN");
        when(userService.createUser(userDTO)).thenReturn(userDTO);

        mockMvc.perform(post("/user-management")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Iñaki"));

    }

    @Test
    @WithMockUser(username = "Admin",roles = "ADMIN")
    void updateUser() throws Exception {

        UserDTO userDTO = new UserDTO("Iñaki","zdbhzh","iñaki@ironhack.com");
        //when(userService.updateUser(userDTO)).thenReturn(userDTO);

        mockMvc.perform(patch("/user-management/update-user-{userId}",1L)
                        .param("user-name","Iñaki")
                        .param("user-password","zdbhzh")
                        .param("user-email","iñaki@ironhack.com")
                        .param("user-roles","ADMIN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.user-name").value("Iñaki"));

    }

}