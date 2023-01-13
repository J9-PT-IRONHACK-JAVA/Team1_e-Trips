package com.ironhack.backend.dto;

import com.ironhack.backend.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
    private Long user_id;
    @NotBlank(message = "Name can't be blank")
    private String username;
    @NotBlank(message = "Password can't be blank")
    private String password;
    @NotBlank(message = "Email can't be blank")
    @Email
    private String email;
    @NotBlank(message = "Role can't be blank")
    private String roles;

    public static UserDTO fromUser(User user){
        var userDTO = new UserDTO();
        userDTO.setUser_id(user.getUser_id());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    public UserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}