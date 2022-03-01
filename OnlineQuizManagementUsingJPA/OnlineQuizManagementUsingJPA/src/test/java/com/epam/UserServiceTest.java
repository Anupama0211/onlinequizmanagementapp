package com.epam;

import com.epam.dao.UserDAO;
import com.epam.entities.User;
import com.epam.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    UserDAO userDAOmock;
    UserService userService;
    User user;

    @BeforeEach
    void setUp() {
        userDAOmock = mock(UserDAO.class);
        userService = new UserService(userDAOmock);
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