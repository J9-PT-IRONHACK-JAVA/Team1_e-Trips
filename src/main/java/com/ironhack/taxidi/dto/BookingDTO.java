package com.ironhack.taxidi.dto;

import com.ironhack.taxidi.enums.BookingType;
import com.ironhack.taxidi.model.Booking;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookingDTO {
    private Long id;
    @NotBlank(message = "Booking type can't be blank")
    private BookingType bookingType;

    public static BookingDTO fromBooking(Booking booking){
        var bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setBookingType(booking.getBookingType());
        return bookingDTO;
    }
}
