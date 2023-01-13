package com.ironhack.backend.service;

import com.ironhack.backend.dto.UserDTO;
import com.ironhack.backend.exceptions.UserNotFoundException;
import com.ironhack.backend.model.User;
import com.ironhack.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        var userList = userRepository.findAll();
        var userDtoList = new ArrayList<UserDTO>();

        for (User u : userList){
            var userDto = UserDTO.fromUser(u);
            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    public Optional<User> findById(long selectedId) {
        return userRepository.findById(selectedId);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public Optional<User> findByNameIgnoreCase(String name) {
        return userRepository.findByUsernameIgnoreCase(name);
    }

    public UserDTO save(UserDTO userDTO) {
        var userToSave = User.fromDTO(userDTO);
        var userDtoReturn = UserDTO.fromUser(userRepository.save(userToSave));

        return userDtoReturn;
    }

    public List<User> saveAll(List<User> listOfUsers) {
        return userRepository.saveAll(listOfUsers);
    }


    public UserDTO createUser(UserDTO user) {
        var userCreated = userRepository.save(User.fromDTO(user));
        return UserDTO.fromUser(userCreated);
    }

    public User updateUser(Long id,
                           Optional<String> userName,
                           Optional<String> password,
                           Optional<String> email,
                           Optional<String> roles) {

        var userToUpdate = findUserById(id);

        userName.ifPresent(userToUpdate::setUsername);
        password.ifPresent(userToUpdate::setPassword);
        email.ifPresent(userToUpdate::setEmail);
        roles.ifPresent(userToUpdate::setRoles);

        return userRepository.save(userToUpdate);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
