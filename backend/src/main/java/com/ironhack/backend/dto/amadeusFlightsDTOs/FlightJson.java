package com.ironhack.backend.dto.amadeusFlightsDTOs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FlightJson {
    private String origin;
    private String destination;
    private String departureDate;
    private String returnDate;
    private Price price;

}
