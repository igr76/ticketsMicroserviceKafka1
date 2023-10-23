package com.example.stmlabs.service;
import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.model.User;
import lombok.NonNull;


import java.util.Optional;

public interface UserService {
    UserDto getUser(String login/*, Authentication authentication*/);

    UserDto updateUser(UserDto newUserDto/*, Authentication authentication*/);

    void deleteUser(String login/*, Authentication authentication*/);

    UserDto greaetUser(UserDto userDto/*, Authentication authentication*/);

    public Optional<User> getByLogin(@NonNull String login);
}
