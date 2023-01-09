package com.ironhack.taxidi.model;

import com.ironhack.taxidi.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @CreationTimestamp
    private Instant creationTime;

    @UpdateTimestamp
    private Instant updateTime;

    private String username;
    private String password;
    private String email;
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> items = new ArrayList<Booking>();

    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public static User fromDTO(UserDTO userDTO){
        var user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        return user;
    }

}
