package com.ironhack.backend.dto;

import com.ironhack.backend.model.FlightBooking;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FlightBookingDTO {
    @NotBlank(message = "departure can't be blank")
    private LocalDateTime departureDateTime;

    @NotBlank(message = "arrival can't be blank")
    private LocalDateTime arrivalDateTime;

    @NotBlank(message = "origin can't be blank")
    private String origin;

    @NotBlank(message = "destination can't be blank")
    private String destination;

    @NotBlank(message = "flight number can't be blank")
    private String flightNumber;

    @NotBlank(message = "airline can't be blank")
    private String airline;

    @NotNull(message = "number of passengers can't be blank")
    private int numPassengers;

    public static FlightBookingDTO fromUser(FlightBooking flightBooking){
        var flightBookingDTO = new FlightBookingDTO();
        flightBookingDTO.setFlightNumber(flightBooking.getFlightNumber());
        flightBookingDTO.setAirline(flightBooking.getAirline());
        flightBookingDTO.setOrigin(flightBooking.getOrigin());
        flightBookingDTO.setDestination(flightBooking.getDestination());
        flightBookingDTO.setNumPassengers(flightBooking.getNumPassengers());
        flightBookingDTO.setArrivalDateTime(flightBooking.getArrivalDateTime());
        flightBookingDTO.setDepartureDateTime(flightBooking.getDepartureDateTime());
        return flightBookingDTO;
    }
}
