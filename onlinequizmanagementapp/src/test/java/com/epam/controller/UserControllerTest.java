package com.epam.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.epam.dto.UserDto;

import com.epam.services.UserDetailsServiceImpl;
import com.epam.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.jar.JarEntry;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsServiceImpl userService;
    @MockBean
    JwtUtil jwtUtil;

    @Test
    void userDetails() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));

    }

    @Test
    void login() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

    }
    @Test
    @WithMockUser(username = "qwerty",roles = {"USER","ADMIN"})
    void adminPortal() throws Exception {
        mockMvc.perform(get("/adminPortal"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminPortal"));
    }

    @Test
    void registerUserAlreadyExists() throws Exception {
        UserDto userDto = new UserDto("Anu", "ADMIN", "1234");
        when(userService.registerUser(any(UserDto.class))).thenThrow(DataIntegrityViolationException.class);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName", "Anu")
                        .param("type", "ADMIN")
                        .param("password", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attribute("message", "User Already Exists"));
    }

    @Test
    void register() throws Exception {
        UserDto userDto = new UserDto("Anupama", "ADMIN", "12345678");
        when(userService.registerUser(any(UserDto.class))).thenReturn(userDto);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("userName", "Anupama")
                        .param("type", "ADMIN")
                        .param("password", "12345678"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attribute("message", "SuccesfulLy Registered!!!"));
    }
}
