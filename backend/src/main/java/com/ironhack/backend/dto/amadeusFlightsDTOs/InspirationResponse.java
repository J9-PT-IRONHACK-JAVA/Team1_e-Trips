package com.ironhack.backend.dto.amadeusFlightsDTOs;

import lombok.Data;

import java.util.List;

@Data
public class InspirationResponse {
    private List<FlightJson> data;
    //private Dictionaries dictionaries;
    //private Meta meta;
}
