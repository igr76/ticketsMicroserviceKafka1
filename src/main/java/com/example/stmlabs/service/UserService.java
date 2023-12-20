package com.example.stmlabs.service;
import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.model.User;


import java.util.Optional;

public interface UserService {
    UserDto getUser(String login/*, Authentication authentication*/);

    UserDto updateUser(UserDto newUserDto/*, Authentication authentication*/);

    void deleteUser(String login/*, Authentication authentication*/);

    UserDto greateUser(UserDto userDto/*, Authentication authentication*/);
    Optional<User> getUserByLogin(String login);

}
