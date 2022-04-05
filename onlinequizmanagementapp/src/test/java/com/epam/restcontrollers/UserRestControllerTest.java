package com.epam.restcontrollers;

import com.epam.dto.UserDto;
import com.epam.exceptions.UserNotFoundException;
import com.epam.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserRestController.class)
class UserRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    static ObjectMapper objectMapper;
    static UserDto userDto;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        userDto = new UserDto();
        userDto.setUserId(22);
        userDto.setPassword("12345");
        userDto.setUserName("Anu");
        userDto.setType("ADMIN");
    }


    @Test
    void login() throws Exception {
        when(userService.validateCredentials(userDto)).thenReturn(true);
        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());

        when(userService.validateCredentials(any(UserDto.class))).thenThrow(UserNotFoundException.class);
        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isNotFound());

    }

    @Test
    void register()throws Exception {
        when(userService.registerUser(userDto)).thenReturn(userDto );
        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());
    }
}