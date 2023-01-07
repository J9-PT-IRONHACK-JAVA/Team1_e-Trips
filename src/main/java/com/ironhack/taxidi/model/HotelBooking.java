package com.ironhack.taxidi.model;

import com.ironhack.taxidi.dto.HotelBookingDTO;
import com.ironhack.taxidi.enums.BookingType;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class HotelBooking extends Booking {
    private String arrivalDate;

    private String departureDate;

    private String hotelAddress;

    private String hotelName;

    private int numGuests;

    public HotelBooking(Long id, BookingType bookingType, User user, String arrivalDate, String departureDate, String hotelAddress, String hotelName, int numGuests) {
        super(id, bookingType, user);
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
