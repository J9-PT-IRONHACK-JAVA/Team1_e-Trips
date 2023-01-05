package com.ironhack.taxidi.model;

import com.ironhack.taxidi.dto.UserDTO;
import com.ironhack.taxidi.enums.UserRoles;
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

    private String user_name;
    private String password;
    private String email;
    private UserRoles role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> items = new ArrayList<Booking>();

    public User(Long user_id, String user_name, String password, String email, UserRoles role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public static User fromDTO(UserDTO userDTO){
        var user = new User();
        user.setUser_name(userDTO.getUser_name());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        return user;
    }

}
