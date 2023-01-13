package com.ironhack.backend.model;

import com.ironhack.backend.dto.HotelBookingDTO;
import com.ironhack.backend.enums.BookingType;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class HotelBooking extends Booking {
    private LocalDate arrivalDate;

    private LocalDate departureDate;

    private String hotelName;

    private int numGuests;

    public HotelBooking(Long id, BookingType bookingType, User user, LocalDate arrivalDate, LocalDate departureDate, String hotelName, int numGuests, BigDecimal price) {
        super(id, bookingType, user, price);
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.hotelName = hotelName;
        this.numGuests = numGuests;
    }

    public static HotelBooking fromDTO(HotelBookingDTO hotelBookingDTO){
        var hotelBooking = new HotelBooking();
        hotelBooking.setArrivalDate(hotelBookingDTO.getArrivalDate());
        hotelBooking.setDepartureDate(hotelBookingDTO.getDepartureDate());
        hotelBooking.setHotelName(hotelBookingDTO.getHotelName());
        hotelBooking.setNumGuests(hotelBookingDTO.getNumGuests());

        return hotelBooking;
    }
}
