package com.ironhack.backend.controller;

import com.ironhack.backend.dto.FlightBookingDTO;
import com.ironhack.backend.dto.FlightDTO;
import com.ironhack.backend.dto.UserDTO;
import com.ironhack.backend.model.User;
import com.ironhack.backend.repository.BookingRepository;
import com.ironhack.backend.repository.UserRepository;
import com.ironhack.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-management")
@RequiredArgsConstructor
public class ManageUsersController {

    private final UserService userService;

    //GET getAllUsers #(sólo admins)

    @GetMapping("/users")
    public List<User> getAllUsers () {
        return userService.findAll();
    }


    //POST registerUser
    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)//pone el codigo 201 si es  OK
    public UserDTO createUser (@RequestBody @Valid UserDTO user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
