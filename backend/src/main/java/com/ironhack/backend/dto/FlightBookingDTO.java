package com.ironhack.backend.dto;

import com.ironhack.backend.model.FlightBooking;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class FlightBookingDTO {
    @NotBlank(message = "departure can't be blank")
    private LocalDate departureDate;

    @NotBlank(message = "arrival can't be blank")
    private LocalDate arrivalDate;

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

    @NotNull(message = "price can't be blank")
    private BigDecimal price;

    public static FlightBookingDTO fromFlightBooking(FlightBooking flightBooking){
        var flightBookingDTO = new FlightBookingDTO();
        flightBookingDTO.setFlightNumber(flightBooking.getFlightNumber());
        flightBookingDTO.setAirline(flightBooking.getAirline());
        flightBookingDTO.setOrigin(flightBooking.getOrigin());
        flightBookingDTO.setDestination(flightBooking.getDestination());
        flightBookingDTO.setNumPassengers(flightBooking.getNumPassengers());
        flightBookingDTO.setArrivalDate(flightBooking.getArrivalDate());
        flightBookingDTO.setDepartureDate(flightBooking.getDepartureDate());
        flightBookingDTO.setPrice(flightBooking.getPrice());
        return flightBookingDTO;
    }

    public static FlightBookingDTO fromFlightDTO(FlightDTO flightDTO){
        var flightBookingDTO = new FlightBookingDTO();
        flightBookingDTO.setOrigin(flightDTO.getOrigin());
        flightBookingDTO.setDestination(flightDTO.getDestination());
        flightBookingDTO.setArrivalDate(flightDTO.getReturnDate());
        flightBookingDTO.setDepartureDate(flightDTO.getDepartureDate());
        flightBookingDTO.setPrice(flightDTO.getPrice());
        flightBookingDTO.setNumPassengers(1);
        return flightBookingDTO;
    }

}
