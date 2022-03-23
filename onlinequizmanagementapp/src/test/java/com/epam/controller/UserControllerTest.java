package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.epam.dto.UserDto;

import com.epam.exceptions.UserNotFoundException;
import com.epam.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void userDetails() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));

    }

    @Test
    void adminPortal() throws Exception {
        mockMvc.perform(get("/adminPortal"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminPortal"));
    }

    @Test
    void loginSuccessful() throws Exception {
        when(userService.validateCredentials(any(UserDto.class))).thenReturn(true);
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName", "Anu")
                        .param("password", "1234")
                        .param("type", "ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminPortal"))
                .andExpect(model().attribute("message", "Succesfully Logged In!!!"));
    }

    @Test
    void loginNotSuccesfulAdmin() throws Exception {
        when(userService.validateCredentials(any(UserDto.class))).thenReturn(false);
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName", "Anu")
                        .param("password", "1234")
                        .param("type", "ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("message", "Wrong UserName Or Password!!!"));
    }

    @Test
    void loginPlayer() throws Exception {
        when(userService.validateCredentials(any(UserDto.class))).thenReturn(false);
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName", "Anu")
                        .param("password", "1234")
                        .param("type", "PLAYER"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("message", "Player Functionalities not defined!!"));
    }

    @Test
    void loginUserDoesNotExist() throws Exception {
        when(userService.validateCredentials(any(UserDto.class))).
                thenThrow(new UserNotFoundException("User doesnt exist!! Register!"));
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName", "Anu")
                        .param("password", "1234")
                        .param("type", "ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("message", "User doesnt exist!! Register!"));
    }

    @Test
    void registerUserAlreadyExists() throws Exception {
        UserDto userDto = new UserDto("Anu", "ADMIN", "1234");
        when(userService.registerUser(any(UserDto.class))).thenReturn(false);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName", "Anu")
                        .param("type", "ADMIN")
                        .param("password", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("message", "User Already Exists"));
    }

    @Test
    void registerAdmin() throws Exception {
        when(userService.registerUser(any(UserDto.class))).thenReturn(true);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName", "Anu")
                        .param("type", "ADMIN")
                        .param("password", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminPortal"))
                .andExpect(model().attribute("message", "SuccesfulLy Registered!!!"));
    }

    @Test
    void registerPlayer() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName", "Anu")
                        .param("type", "PLAYER")
                        .param("password", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("message", "Player Functionalities not defined!!"));
    }
}
