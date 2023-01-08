package com.ironhack.taxidi.dto;

import com.ironhack.taxidi.model.HotelBooking;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelBookingDTO {
    @NotBlank(message = "arrival date can't be blank")
    private LocalDate arrivalDate;

    @NotBlank(message = "departure date can't be blank")
    private LocalDate departureDate;

    @NotBlank(message = "address date can't be blank")
    private String hotelAddress;

    @NotBlank(message = "hotel name date can't be blank")
    private String hotelName;

    @NotNull(message = "number of guests date can't be blank")
    private int numGuests;

    public static HotelBookingDTO fromUser(HotelBooking hotelBooking){
        var hotelBookingDTO = new HotelBookingDTO();
        hotelBookingDTO.setArrivalDate(hotelBooking.getArrivalDate());
        hotelBookingDTO.setDepartureDate(hotelBooking.getDepartureDate());
        hotelBookingDTO.setHotelAddress(hotelBooking.getHotelAddress());
        hotelBookingDTO.setHotelName(hotelBooking.getHotelName());
        hotelBookingDTO.setNumGuests(hotelBooking.getNumGuests());
        return hotelBookingDTO;
    }
}
