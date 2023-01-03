package com.ironhack.taxidi.model;

import com.ironhack.taxidi.enums.UserRoles;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant creationTime;

    @UpdateTimestamp
    private Instant updateTime;

    private String name;
    private String password;
    private String email;
    private UserRoles role;

    @OneToMany(mappedBy = "userId")
    private List<Booking> bookings;




}
