package com.ironhack.backend.dto;

import com.ironhack.backend.enums.BookingType;
import com.ironhack.backend.model.Booking;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookingDTO {
    private Long id;
    @NotBlank(message = "Booking type can't be blank")
    private BookingType bookingType;
    private BigDecimal price;
    private String name;
    private String purpose;
    private Integer travelers;

    public static BookingDTO fromBooking(Booking booking){
        var bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setBookingType(booking.getBookingType());
        bookingDTO.setPrice(booking.getPrice());
        bookingDTO.setName(booking.getName());
        bookingDTO.setPurpose(booking.getPurpose());
        bookingDTO.setTravelers(booking.getTravelers());
        return bookingDTO;
    }
}
