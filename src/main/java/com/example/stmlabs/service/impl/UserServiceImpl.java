package com.example.stmlabs.service.impl;


import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.model.User;
import com.example.stmlabs.repository.UserRepository;
import com.example.stmlabs.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Сервис пользователей
 */
//@AllArgsConstructor
@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

// private final UserMapper userMapper;




  /**
   * Получить данные пользователя
   */
  @Override
  public UserDto getUser(String login/*, Authentication authentication*/) {
    log.info("Получить данные пользователя" );
    UserDto userDto = new UserDto();
    User user= new User();
    user=userRepository.findByLogin( login).orElseThrow();
    userDto.setName(user.getName());
    userDto.setLogin(user.getLogin());
    userDto.setPasswordHash(user.getPasswordHash());
    userDto.setSurname(user.getSurname());
    userDto.setPatronymicName(user.getPatronymicName());
//    String nameEmail = authentication.getName();
//    UserEntity userEntity = findEntityByEmail(nameEmail);
    return userDto;
  }

  /**
   * Обновить данные пользователя
   */
  @Override
  public UserDto updateUser(UserDto newUserDto/*, Authentication authentication*/) {
    log.info("Получить данные пользователя");
    return null;
  }

  @Override
  public void deleteUser(String login/*, Authentication authentication*/) {

  }

  @Override
  public UserDto greaetUser(UserDto userDto/*, Authentication authentication*/) {
    User user= new User();
    user.setName(userDto.getName());
    user.setLogin(userDto.getLogin());
    user.setPasswordHash(userDto.getPasswordHash());
    user.setSurname(userDto.getSurname());
    user.setPatronymicName(userDto.getPatronymicName());
    userRepository.save(user);
    return userDto;
  }

  @Override
  public Optional<User> getByLogin(@NonNull String login) {
    return null;
  }


}
