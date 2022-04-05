package com.epam.controller;

import com.epam.dto.UserDto;
import com.epam.exceptions.UserNotFoundException;
import com.epam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class UserController {
    @Autowired
    UserService userService;
    private static final String MESSAGE = "message";
    private static final String ADMINPORTAL = "adminPortal";

    @RequestMapping("/")
    public String userDetails() {
        return "home";
    }

    @RequestMapping("adminPortal")
    public String adminPortal() {
        return ADMINPORTAL;
    }

    @PostMapping("login")
    public ModelAndView login(@Valid UserDto userDto) {
        ModelAndView modelView = new ModelAndView();
        try {
            if (userDto.getType().equalsIgnoreCase("ADMIN")) {
                boolean status = userService.validateCredentials(userDto);
                if (status) {
                    modelView.addObject(MESSAGE, "Succesfully Logged In!!!");
                    modelView.setViewName(ADMINPORTAL);
                } else {
                    modelView.addObject(MESSAGE, "Wrong UserName Or Password!!!");
                    modelView.setViewName("home");
                }
            } else {
                modelView.addObject(MESSAGE, "Player Functionalities not defined!!");
                modelView.setViewName("home");
            }

        } catch (UserNotFoundException e) {
            modelView.addObject(MESSAGE, e.getMessage());
            modelView.setViewName("home");
        }

        return modelView;
    }

    @PostMapping("register")
    public ModelAndView register(@Valid UserDto userDto) {
        ModelAndView modelView = new ModelAndView();
        try {
            userService.registerUser(userDto);
            if (userDto.getType().equalsIgnoreCase("ADMIN")) {
                modelView.addObject(MESSAGE, "SuccesfulLy Registered!!!");
                modelView.setViewName(ADMINPORTAL);
            } else {
                modelView.addObject(MESSAGE, "Player Functionalities not defined!!");
                modelView.setViewName("home");
            }
        } catch (DataIntegrityViolationException e) {
            modelView.addObject(MESSAGE, "User Already Exists");
            modelView.setViewName("home");
        }
        return modelView;
    }
}
