package com.example.stmlabs.service.impl;


import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.exception.AuthException;
import com.example.stmlabs.exception.ElemNotFound;
import com.example.stmlabs.mapper.UserMapper;
import com.example.stmlabs.model.User;
import com.example.stmlabs.repository.UserRepository;
import com.example.stmlabs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Optional;
/**
 * Реализация сервиса пользователей
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
  public UserDto getUser(String login, Authentication authentication) {
    log.info("Получить данные пользователя" );
    if (!checkAuthor(login, authentication)) {
       throw new AuthException("Вы не можете получать чужую запись");
    }
    User user= new User();
    user=userRepository.findByLogin( login).orElseThrow(()->
            new ElemNotFound("Такого пользователя не существует"));
    return userMapper.toDTO(user);
  }

  /**   Обновить данные пользователя*/
  @Override
  public UserDto updateUser(UserDto newUserDto, Authentication authentication) {
    log.info("Обновить данные пользователя");
    if (!checkAuthor(newUserDto.getLogin(), authentication)) {
      throw new AuthException("Вы не можете менять чужую запись");
    }
    if (userRepository.findByLogin(newUserDto.getLogin()).isEmpty()) {
      throw  new ElemNotFound("Такого пользователя не существует");
    }else userRepository.save(userMapper.toEntity(newUserDto));
    return newUserDto;
  }
  /**   Удалить пользователя*/
  @Override
  public void deleteUser(String login, Authentication authentication) {
    log.info("Удалить пользователя");
    if (!checkAuthor(login, authentication)) {
      throw new AuthException("Вы не можете менять чужую запись");
    }
    User user= new User();
    user= userRepository.findByLogin(login).orElseThrow(()->
            new ElemNotFound("Такого пользователя не существует"));
    userRepository.delete(user);
  }
  /**   Создать пользователя*/
  @Override
  public UserDto greateUser(UserDto userDto, Authentication authentication) {
    log.info("Создать пользователя");
    if (!checkAuthor(userDto.getLogin(), authentication)) {
      throw new AuthException("Вы не можете менять чужую запись");
    }
    if (!userRepository.findByLogin(userDto.getLogin()).isEmpty()) {
      throw new UnsupportedOperationException("Такой пользователь уже существует");
    }else userRepository.save(userMapper.toEntity(userDto));
    return userDto;
  }
  /**   Получить  пользователя по логину */
  @Override
  public Optional<User> getUserByLogin(String login) {
    return userRepository.findByLogin(login);
  }

  /**   Проверка на авторство пользователя*/
  public boolean checkAuthor(String login, Authentication authentication) {
    if (userRepository.findByLogin(login)!=null && login== authentication.getName()) {return  true;
    }else return false;
  }
}
