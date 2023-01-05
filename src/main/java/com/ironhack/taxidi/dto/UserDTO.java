package com.ironhack.taxidi.dto;

import com.ironhack.taxidi.enums.UserRoles;
import com.ironhack.taxidi.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    private Long user_id;
    @NotBlank(message = "Name can't be blank")
    private String user_name;
    @NotBlank(message = "Password can't be blank")
    private String password;
    @NotBlank(message = "Email can't be blank")
    @Email
    private String email;
    @NotBlank(message = "Role can't be blank")
    private UserRoles role;

    public static UserDTO fromUser(User user){
        var userDTO = new UserDTO();
        userDTO.setUser_id(user.getUser_id());
        userDTO.setUser_name(user.getUser_name());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}