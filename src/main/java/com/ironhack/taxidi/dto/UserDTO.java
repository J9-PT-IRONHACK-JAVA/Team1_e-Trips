package com.ironhack.taxidi.dto;

import com.ironhack.taxidi.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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
    private String role;

    public static UserDTO fromUser(User user){
        var userDTO = new UserDTO();
        userDTO.setUser_id(user.getUser_id());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}