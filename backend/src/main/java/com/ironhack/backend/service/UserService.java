package com.ironhack.backend.service;

import com.ironhack.backend.dto.UserDTO;
import com.ironhack.backend.exceptions.UserNotFoundException;
import com.ironhack.backend.model.User;
import com.ironhack.backend.repository.UserRepository;
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


    public UserDTO createUser(UserDTO user) {
        var userCreated = userRepository.save(User.fromDTO(user));
        return UserDTO.fromUser(userCreated);
    }

    public User updateUser(Long id, User user) {
        var userToUpdate = findUserById(id);
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setRoles(user.getRoles());
        return userRepository.save(userToUpdate);
    }


    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
