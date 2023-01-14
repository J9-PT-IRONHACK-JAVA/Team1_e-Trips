package com.ironhack.backend.model;

import com.ironhack.backend.dto.FlightBookingDTO;
import com.ironhack.backend.enums.BookingType;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
public class FlightBooking extends Booking{
    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private String origin;

    private String destination;

    private String flightNumber;

    private String airline;

    private int numPassengers;

    public FlightBooking(Long id, BookingType bookingType, User user, LocalDate departureDate, LocalDate arrivalDate,
                         String origin, String destination, String flightNumber, String airline, int numPassengers, BigDecimal price) {
        super(id, bookingType, user, price);
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.numPassengers = numPassengers;
    }
    public static FlightBooking fromDTO(Optional<User> user, FlightBookingDTO flightBookingDTO){
        var flightBooking = new FlightBooking();
        flightBooking.setUser(user.get());
        flightBooking.setBookingType(BookingType.FLIGHT);
        flightBooking.setPrice(flightBookingDTO.getPrice());
        flightBooking.setFlightNumber(flightBookingDTO.getFlightNumber());
        flightBooking.setNumPassengers(flightBookingDTO.getNumPassengers());
        flightBooking.setTravelers(flightBookingDTO.getNumPassengers());
        flightBooking.setAirline(flightBookingDTO.getAirline());
        flightBooking.setOrigin(flightBookingDTO.getOrigin());
        flightBooking.setDestination(flightBookingDTO.getDestination());
        flightBooking.setDepartureDate(flightBookingDTO.getDepartureDate());
        flightBooking.setArrivalDate(flightBookingDTO.getArrivalDate());
        return flightBooking;
    }
}
