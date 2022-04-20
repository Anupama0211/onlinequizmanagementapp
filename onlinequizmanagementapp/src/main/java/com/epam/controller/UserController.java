package com.epam.controller;

import com.epam.dto.UserDto;
import com.epam.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class UserController {
    @Autowired
    UserDetailsServiceImpl userService;
    private static final String MESSAGE = "message";
    private static final String ADMINPORTAL = "adminPortal";
    private static final String REGISTER ="register";

    @RequestMapping("/")
    public String userDetails() {
        return REGISTER;
    }

    @RequestMapping("adminPortal")
    public String adminPortal() {
        return ADMINPORTAL;
    }

    @RequestMapping("login")
    public String login(UserDto userDto) {
        return "login";
    }

    @PostMapping("register")
    public ModelAndView register(UserDto userDto) {
        userDto.setRole("ROLE_" + userDto.getRole());
        ModelAndView modelView = new ModelAndView();
        try {
            userService.registerUser(userDto);
            modelView.addObject(MESSAGE, "SuccesfulLy Registered!!!");
            modelView.setViewName(REGISTER);

        } catch (DataIntegrityViolationException e) {
            modelView.addObject(MESSAGE, "User Already Exists");
            modelView.setViewName(REGISTER);
        }
        return modelView;
    }
}
