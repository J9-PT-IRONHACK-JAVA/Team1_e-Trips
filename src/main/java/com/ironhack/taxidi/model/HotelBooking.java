package com.ironhack.taxidi.model;

import com.ironhack.taxidi.dto.HotelBookingDTO;
import com.ironhack.taxidi.enums.BookingType;
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

    private String hotelAddress;

    private String hotelName;

    private int numGuests;

    public HotelBooking(Long id, BookingType bookingType, User user, LocalDate arrivalDate, LocalDate departureDate, String hotelAddress, String hotelName, int numGuests, BigDecimal price) {
        super(id, bookingType, user, price);
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.hotelAddress = hotelAddress;
        this.hotelName = hotelName;
        this.numGuests = numGuests;
    }

    public static HotelBooking fromDTO( HotelBooking hotelBookingDTO){
        var hotelBooking = new HotelBooking();
        hotelBooking.setArrivalDate(hotelBookingDTO.getArrivalDate());
        hotelBooking.setDepartureDate(hotelBookingDTO.getDepartureDate());
        hotelBooking.setHotelAddress(hotelBookingDTO.getHotelAddress());
        hotelBooking.setHotelName(hotelBookingDTO.getHotelName());
        hotelBooking.setNumGuests(hotelBookingDTO.getNumGuests());

        return hotelBooking;
    }
}
