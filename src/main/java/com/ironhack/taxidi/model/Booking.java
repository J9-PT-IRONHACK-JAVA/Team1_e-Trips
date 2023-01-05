package com.ironhack.taxidi.model;

import com.ironhack.taxidi.dto.BookingDTO;
import com.ironhack.taxidi.enums.BookingType;
import com.ironhack.taxidi.enums.UserRoles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booked_by")
    private User user;

    public Booking(Long id, BookingType bookingType, User user) {
        this.id = id;
        this.bookingType = bookingType;
        this.user = user;
    }
    public static Booking fromDTO(BookingDTO bookingDTO){
        var booking = new Booking();
        booking.setBookingType(bookingDTO.getBookingType());
        booking.setId(bookingDTO.getId());
        return booking;
    }
}
