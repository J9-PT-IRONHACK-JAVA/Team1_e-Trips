package com.ironhack.backend.dto.amadeusFlightsDTOs;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class InspirationResponse {
    private List<FlightJson> data;
}
