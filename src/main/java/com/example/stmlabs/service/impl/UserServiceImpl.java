package com.example.stmlabs.service.impl;


import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.model.User;
import com.example.stmlabs.repository.UserRepository;
import com.example.stmlabs.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Сервис пользователей
 */
@Service
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
  private UserRepository userRepository;
  private  User user;
  private   UserDto userDto;

//  public UserServiceImpl(UserRepository userRepository, User user, UserDto userDto) {
//    this.userRepository = userRepository;
//    this.user = user;
//    this.userDto = userDto;
//  }

// private final UserMapper userMapper;


//  @Value("${image.user.dir.path}")
//  private String userPhotoDir;


  /**
   * Получить данные пользователя
   */
  @Override
  public UserDto getUser(String login/*, Authentication authentication*/) {
    log.info("Получить данные пользователя" );
    UserDto userDto = new UserDto();
    User user= new User();
//    user=userRepository.findByLogin( login).orElseThrow();
    userDto.setName("ttytt");
    userDto.setLogin("gjguyguyf");
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
    return null;
  }

  @Override
  public Optional<User> getByLogin(@NonNull String login) {
    return null;
  }


}
