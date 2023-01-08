package com.ironhack.backend.model;

import com.ironhack.backend.dto.BookingDTO;
import com.ironhack.backend.enums.BookingType;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant creationTime;

    @UpdateTimestamp
    private Instant updateTime;
    private BookingType bookingType;

    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booked_by")
    private User user;

    public Booking(Long id, BookingType bookingType, User user, BigDecimal price) {
        this.id = id;
        this.bookingType = bookingType;
        this.user = user;
        this.price = price;
    }
    public static Booking fromDTO(BookingDTO bookingDTO){
        var booking = new Booking();
        booking.setBookingType(bookingDTO.getBookingType());
        booking.setId(bookingDTO.getId());
        booking.setPrice(bookingDTO.getPrice());
        return booking;
    }
}
