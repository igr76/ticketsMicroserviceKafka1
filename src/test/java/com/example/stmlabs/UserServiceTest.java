package com.example.stmlabs;

import com.example.stmlabs.dto.Role;
import com.example.stmlabs.dto.UserDto;
import com.example.stmlabs.exception.ElemNotFound;
import com.example.stmlabs.mapper.UserMapper;
import com.example.stmlabs.model.User;
import com.example.stmlabs.repository.UserRepository;
import com.example.stmlabs.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl(userRepository,userMapper);

    @Test
    void getUserTest() {
        User user = getUser();UserDto userDto = getUserDto();
        Authentication authentication = new TestingAuthenticationToken(userDto.getLogin(), userDto.getPasswordHash(), String.valueOf(Role.USER));
        authentication.setAuthenticated(true);
        when(userRepository.findByLogin("login")).thenReturn(Optional.ofNullable(user));
        when(userRepository.findByLoginIsFalse(anyString())).thenReturn(false);
        when(userMapper.toDTO(any())).thenReturn(userDto);
        assertThat(userService.getUser("login",authentication)).isEqualTo(userDto);
        verify(userRepository, times(1)).findByLogin(any());
    }
    @Test
    void getUserTestNegative() {
        UserDto userDto = getUserDto();
        Authentication authentication = new TestingAuthenticationToken(userDto.getLogin(), userDto.getPasswordHash(), String.valueOf(Role.USER));
        authentication.setAuthenticated(true);
        when(userRepository.findByLoginIsFalse(anyString())).thenReturn(false);
        when(userRepository.findByLogin(any())).thenReturn(Optional.ofNullable(null));
        assertThatExceptionOfType(ElemNotFound.class).isThrownBy(() -> userService.getUser("login",authentication));
        verify(userRepository, times(1)).findByLogin(any());
    }
    @Test
    void greateUserTest() {
        User user = getUser();UserDto userDto = getUserDto();
        Authentication authentication = new TestingAuthenticationToken(userDto.getLogin(), userDto.getPasswordHash(), String.valueOf(Role.USER));
        authentication.setAuthenticated(true);
        when(userRepository.findByLoginIsFalse(anyString())).thenReturn(false);
        //  when(userRepository.findByLogin(any())).thenReturn(null);
        assertThat(userService.greateUser(userDto,authentication)).isEqualTo(userDto);
        verify(userRepository, times(1)).findByLogin(any());
    }
    @Test
    void greateUserTestNegative() {
        UserDto userDto = getUserDto();
        Authentication authentication = new TestingAuthenticationToken(userDto.getLogin(), userDto.getPasswordHash(), String.valueOf(Role.USER));
        authentication.setAuthenticated(true);
        when(userRepository.findByLoginIsFalse(anyString())).thenReturn(false);
        when(userRepository.findByLogin(any())).thenReturn(Optional.ofNullable(getUser()));
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> userService.greateUser(userDto,authentication));
        verify(userRepository, times(1)).findByLogin(any());
    }
    @Test
    void updateUserTest() {
        User user = getUser();UserDto userDto = getUserDto();
        Authentication authentication = new TestingAuthenticationToken(userDto.getLogin(), userDto.getPasswordHash(), String.valueOf(Role.USER));
        authentication.setAuthenticated(true);
        when(userRepository.findByLoginIsFalse(anyString())).thenReturn(false);
        when(userRepository.findByLogin(any())).thenReturn(Optional.ofNullable(user));
        when(userMapper.toEntity(any())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
        assertThat(userService.updateUser(userDto,authentication)).isEqualTo(userDto);
        verify(userRepository, times(1)).findByLogin(any());
    }
    @Test
    void updateUserTestNegative() {
        UserDto userDto = getUserDto();
        Authentication authentication = new TestingAuthenticationToken(userDto.getLogin(), userDto.getPasswordHash(), String.valueOf(Role.USER));
        authentication.setAuthenticated(true);
        when(userRepository.findByLoginIsFalse(anyString())).thenReturn(false);
        when(userRepository.findByLogin(any())).thenReturn(Optional.ofNullable(null));
        assertThatExceptionOfType(ElemNotFound.class).isThrownBy(() -> userService.updateUser(userDto,authentication));
        verify(userRepository, times(1)).findByLogin(any());
    }
    @Test
    void deleteUserTest() {
        User user = getUser();UserDto userDto = getUserDto();
        Authentication authentication = new TestingAuthenticationToken(userDto.getLogin(), userDto.getPasswordHash(), String.valueOf(Role.USER));
        authentication.setAuthenticated(true);
        when(userRepository.findByLoginIsFalse(anyString())).thenReturn(false);
        when(userRepository.findByLogin(any())).thenReturn(Optional.ofNullable(user));
        userService.deleteUser("login",authentication);;
        verify(userRepository, times(1)).findByLogin(any());
    }
    @Test
    void deleteUserTestNegative() {
        UserDto userDto = getUserDto();
        Authentication authentication = new TestingAuthenticationToken(userDto.getLogin(), userDto.getPasswordHash(), String.valueOf(Role.USER));
        authentication.setAuthenticated(true);
        when(userRepository.findByLoginIsFalse(anyString())).thenReturn(false);
        when(userRepository.findByLogin(any())).thenReturn(Optional.ofNullable(null));
        assertThatExceptionOfType(ElemNotFound.class).isThrownBy(() -> userService.deleteUser("login",authentication));
        verify(userRepository, times(1)).findByLogin(any());
    }

    User getUser() {
        User user =new User(1,"login","1111",
                "name1","surname","patronymicName");
        return user;
    }

    UserDto getUserDto() {
        UserDto userDto = new UserDto("login","1111",
                "name1","surname","patronymicName");
        return userDto;
    }
}
