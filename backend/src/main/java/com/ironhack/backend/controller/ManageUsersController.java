package com.ironhack.backend.controller;

import com.ironhack.backend.dto.UserDTO;
import com.ironhack.backend.model.User;
import com.ironhack.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-management")
@RequiredArgsConstructor
public class ManageUsersController {

    private final UserService userService;

    //GET getAllUsers #(sólo admins)
    @GetMapping("/users")
    public List<UserDTO> getAllUsers () {
        return userService.findAll();
    }


    //POST registerUser
    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)//pone el codigo 201 si es  OK
    public UserDTO createUser (@RequestBody @Valid UserDTO user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserDTO updateFullUser(@PathVariable("id") Long id, @RequestBody UserDTO user){
        return userService.updateFullUser(id, user);
    }

    @PatchMapping("/update-user-{userId}")
    public UserDTO updateUser(@PathVariable("userId") Long userId,
                               @RequestParam("user-name") Optional<String> userName,
                               @RequestParam("user-password") Optional<String> password,
                               @RequestParam("user-email") Optional<String> email,
                               @RequestParam("user-roles") Optional<String> roles){
        return userService.updateUser(userId,userName,password,email,roles);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
