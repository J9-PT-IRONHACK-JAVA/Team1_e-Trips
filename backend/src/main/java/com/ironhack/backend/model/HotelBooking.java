package com.ironhack.backend.model;

import com.ironhack.backend.enums.BookingType;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< Updated upstream:src/main/java/com/ironhack/taxidi/model/HotelBooking.java
import java.util.Date;
=======
import java.math.BigDecimal;
import java.time.LocalDate;
>>>>>>> Stashed changes:backend/src/main/java/com/ironhack/backend/model/HotelBooking.java

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
