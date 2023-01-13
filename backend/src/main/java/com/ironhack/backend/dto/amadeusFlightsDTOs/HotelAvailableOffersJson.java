package com.ironhack.backend.dto.amadeusFlightsDTOs;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class HotelAvailableOffersJson {
    private HotelNameJson hotel;
    private List<HotelOfferJson> offers;
}
