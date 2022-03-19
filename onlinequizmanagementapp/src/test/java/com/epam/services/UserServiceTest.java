package com.epam.services;


import com.epam.dao.UserRepository;
import com.epam.dto.UserDto;
import com.epam.entities.User;
import com.epam.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;
    @Mock
    ModelMapper modelMapper;
    User user;
    UserDto userDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(22);
        user.setPassword("12345");
        user.setUserName("Anu");
        user.setType("ADMIN");
        userDto = new UserDto();
        userDto.setUserId(22);
        userDto.setPassword("12345");
        userDto.setUserName("Anu");
        userDto.setType("ADMIN");
    }

    @Test
    void registerUser() {
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(userDto,User.class)).thenReturn(user);
        assertThat(userService.registerUser(userDto)).isTrue();
        when(userRepository.save(user)).thenThrow(DataIntegrityViolationException.class);
        assertThat(userService.registerUser(userDto)).isFalse();
    }

     @Test
    void validateCredentials() throws UserNotFoundException {
        when(userRepository.findDistinctByUserName("Anu")).thenReturn(Optional.ofNullable(user));
        assertThat(userService.validateCredentials("Anu", "12345", "ADMIN")).isTrue();
        assertThat(userService.validateCredentials("Anu", "12345", "PLAYER")).isFalse();
        assertThat(userService.validateCredentials("Anu", "123456", "ADMIN")).isFalse();
        when(userRepository.findDistinctByUserName("Anu")).thenReturn(Optional.ofNullable(null));
        assertThrows(UserNotFoundException.class, () -> userService.validateCredentials("Anu", "12345", "ADMIN"));
    }
}