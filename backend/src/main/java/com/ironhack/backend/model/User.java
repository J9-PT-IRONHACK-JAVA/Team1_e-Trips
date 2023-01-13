package com.ironhack.backend.model;

import com.ironhack.backend.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String username;
    private String password;

    private String email;

    private String roles;
    private Boolean isAccountNonLocked;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant lastUpdatedAt;

    public User(String username, String password,String email, String roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        isAccountNonLocked = true;
    }
    public static User fromDTO(UserDTO userDTO){
        var user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRoles("ROLE_USER");
        return user;
    }

    public User() {
        isAccountNonLocked = true;
    }
}
