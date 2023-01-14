package com.ironhack.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBookingsDTO {
    private List<FlightBookingDTO> flights;
    private List<HotelBookingDTO> hotels;

}
