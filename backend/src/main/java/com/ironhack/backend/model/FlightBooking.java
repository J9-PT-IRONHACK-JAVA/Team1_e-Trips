package com.ironhack.backend.model;

import com.ironhack.backend.enums.BookingType;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Entity
@NoArgsConstructor
public class FlightBooking extends Booking{
    private LocalDateTime departureDateTime;

    private LocalDateTime arrivalDateTime;

    private String origin;

    private String destination;

    private String flightNumber;

    private String airline;

    private int numPassengers;

    public FlightBooking(Long id, BookingType bookingType, User user, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, String origin, String destination, String flightNumber, String airline, int numPassengers, BigDecimal price) {
        super(id, bookingType, user, price);
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.numPassengers = numPassengers;
    }
    public static FlightBooking fromDTO( FlightBooking flightBookingDTO){
        var flightBooking = new FlightBooking();
        flightBooking.setFlightNumber(flightBookingDTO.getFlightNumber());
        flightBooking.setNumPassengers(flightBookingDTO.getNumPassengers());
        flightBooking.setAirline(flightBookingDTO.getAirline());
        flightBooking.setOrigin(flightBookingDTO.getOrigin());
        flightBooking.setDestination(flightBookingDTO.getDestination());
        flightBooking.setDepartureDateTime(flightBookingDTO.getDepartureDateTime());
        flightBooking.setArrivalDateTime(flightBookingDTO.getArrivalDateTime());
        return flightBooking;
    }
}
