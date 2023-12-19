package com.example.stmlabs.service.impl;


import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.exception.ElemNotFound;
import com.example.stmlabs.mapper.UserMapper;
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
//@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
  private  UserRepository userRepository;
  private  UserMapper userMapper;
  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  /**
   * Получить данные пользователя
   */
  @Override
  public UserDto getUser(String login/*, Authentication authentication*/) {
    log.info("Получить данные пользователя" );
    User user= new User();
    user=userRepository.findByLogin( login).orElseThrow(()->
            new ElemNotFound("Такого пользователя не существует"));
    return userMapper.toDTO(user);
  }

  /**
   * Обновить данные пользователя
   */
  @Override
  public UserDto updateUser(UserDto newUserDto/*, Authentication authentication*/) {
    log.info("Обновить данные пользователя");
    if (userRepository.findByLogin(newUserDto.getLogin()).isEmpty()) {
      throw  new ElemNotFound("Такого пользователя не существует");
    }else userRepository.save(userMapper.toEntity(newUserDto));
    return newUserDto;
  }

  @Override
  public void deleteUser(String login) {
    log.info("Удалить пользователя");
    User user= new User();
    user= userRepository.findByLogin(login).orElseThrow(()->
            new ElemNotFound("Такого пользователя не существует"));
    userRepository.delete(user);
  }

  @Override
  public UserDto greateUser(UserDto userDto) {
    log.info("Создать пользователя");
    if (!userRepository.findByLogin(userDto.getLogin()).isEmpty()) {
      throw new UnsupportedOperationException("Такой пользователь уже существует");
    }else userRepository.save(userMapper.toEntity(userDto));
    return userDto;
  }




}
