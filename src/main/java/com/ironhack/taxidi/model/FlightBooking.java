package com.ironhack.taxidi.model;

import com.ironhack.taxidi.dto.BookingDTO;
import com.ironhack.taxidi.enums.BookingType;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class FlightBooking extends Booking{
    private Date departureDateTime;

    private Date arrivalDateTime;

    private String origin;

    private String destination;

    private String flightNumber;

    private String airline;

    private Number numPassengers;

    public FlightBooking(Long id, BookingType bookingType, User user, Date departureDateTime, Date arrivalDateTime, String origin, String destination, String flightNumber, String airline, Number numPassengers) {
        super(id, bookingType, user);
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
