package com.ironhack.backend.dto.amadeusFlightsDTOs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class HotelOfferJson {

    private String id;
    private String checkInDate;
    private String checkOutDate;
    private HotelPriceJson price;
}
