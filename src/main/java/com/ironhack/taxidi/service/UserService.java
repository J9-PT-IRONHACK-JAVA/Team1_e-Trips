package com.ironhack.taxidi.service;


import com.ironhack.taxidi.model.User;
import com.ironhack.taxidi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(long selectedId) {
        return userRepository.findById(selectedId);
    }

    public Optional<User> findByNameIgnoreCase(String name) {
        return userRepository.findByUsernameIgnoreCase(name);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> saveAll(List<User> listOfUsers) {
        return userRepository.saveAll(listOfUsers);
    }

}
