package com.example.stmlabs.service;
import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.model.User;
import org.springframework.security.core.Authentication;


import java.util.Optional;
/**
 * Сервис пользователей
 */
public interface UserService {
    /**   Получить данные пользователя */
    UserDto getUser(String login, Authentication authentication);
    /**   Обновить данные пользователя*/
    UserDto updateUser(UserDto newUserDto, Authentication authentication);
    /**   Удалить пользователя*/
    void deleteUser(String login, Authentication authentication);
    /**   Создать пользователя*/
    UserDto greateUser(UserDto userDto, Authentication authentication);
    /**   Получить  пользователя по логину */
    Optional<User> getUserByLogin(String login);

}
