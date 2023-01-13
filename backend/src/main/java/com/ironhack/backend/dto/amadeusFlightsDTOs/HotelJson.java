package com.ironhack.backend.dto.amadeusFlightsDTOs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HotelJson {
    private String name;
    private String hotelId;
    private Integer rating;
}
