package com.ironhack.backend.dto;

import com.ironhack.backend.model.HotelBooking;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
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

    @NotNull(message = "price can't be blank")
    private BigDecimal price;
    public static HotelBookingDTO fromHotelBooking(HotelBooking hotelBooking){
        var hotelBookingDTO = new HotelBookingDTO();
        hotelBookingDTO.setArrivalDate(hotelBooking.getArrivalDate());
        hotelBookingDTO.setDepartureDate(hotelBooking.getDepartureDate());
        hotelBookingDTO.setHotelName(hotelBooking.getHotelName());
        hotelBookingDTO.setNumGuests(hotelBooking.getNumGuests());
        return hotelBookingDTO;
    }

    public static HotelBookingDTO fromHotelDTO(HotelDTO hotelDTO){
        var hotelBookingDTO = new HotelBookingDTO();
        hotelBookingDTO.setArrivalDate(hotelDTO.getCheckInDate());
        hotelBookingDTO.setDepartureDate(hotelDTO.getCheckOutDate());
        hotelBookingDTO.setHotelName(hotelDTO.getName());
        hotelBookingDTO.setNumGuests(hotelDTO.getGuests());
        return hotelBookingDTO;
    }
}
