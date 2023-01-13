package com.ironhack.backend.dto.amadeusFlightsDTOs;

import lombok.Data;

import java.util.List;

@Data
public class HotelAvailabilityResponse {

    private List<HotelAvailableOffersJson> data;

}
