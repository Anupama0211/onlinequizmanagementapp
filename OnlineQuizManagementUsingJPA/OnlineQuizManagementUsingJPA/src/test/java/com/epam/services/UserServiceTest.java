package com.epam.services;

import com.epam.dao.UserDAO;
import com.epam.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserDAO userDAOmock;
    @InjectMocks
    UserService userService;
    User user;

    @BeforeEach
    void setUp() {

        User user = new User();
        user.setUserId(22);
        user.setPassword("12345");
        user.setUserName("Anu");
        user.setType("Admin");
    }

    @Test
    void registerUser() {
        userService.registerUser(user);
        verify(userDAOmock).insert(user);
    }

    @Test
    void validateCredentials() {
        when(userDAOmock.getUser("Anu")).thenReturn(Optional.ofNullable(user));
        assertFalse(userService.validateCredentials("Anu", "12345", 1));
        assertFalse(userService.validateCredentials("Anu", "1235", 1));
        assertFalse(userService.validateCredentials("Au", "12345", 1));
        assertFalse(userService.validateCredentials("Anu", "12345", 2));
    }
}