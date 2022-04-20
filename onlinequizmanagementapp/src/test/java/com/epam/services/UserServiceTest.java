package com.epam.services;


import com.epam.dao.UserRepository;
import com.epam.dto.UserDto;
import com.epam.entities.User;
import com.epam.exceptions.UserNotFoundException;
import com.epam.model.UserDetailsModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserDetailsServiceImpl userService;
    @Mock
    ModelMapper modelMapper;
    User user;
    UserDto userDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId(22);
        user.setPassword("12345");
        user.setUsername("Anu");
        user.setRole("ADMIN");
        userDto = new UserDto();
        userDto.setUserId(22);
        userDto.setPassword("12345");
        userDto.setUsername("Anu");
        userDto.setRole("ADMIN");
    }

    @Test
    void registerUser() {
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(modelMapper.map(user,UserDto.class)).thenReturn(userDto);
        assertThat(userService.registerUser(userDto)).isEqualTo(userDto);
    }

    @Test
    void loadByUsername(){
        when(userRepository.findDistinctByUsername("Anupama")).thenReturn(Optional.ofNullable(user));
        assertThat(userService.loadUserByUsername("Anupama")).isInstanceOf(UserDetails.class);
        when(userRepository.findDistinctByUsername("Anupama")).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->userService.loadUserByUsername("Anupama"));
    }

}