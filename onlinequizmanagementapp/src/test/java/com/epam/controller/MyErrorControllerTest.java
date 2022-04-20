package com.epam.controller;

import com.epam.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = MyErrorController.class)
class MyErrorControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserDetailsService userDetailsService;
    @MockBean
    JwtUtil jwtUtil;

    @Test
    @WithMockUser(username = "qwerty", roles = {"USER", "ADMIN"})
    void handleError() throws Exception {
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }
}