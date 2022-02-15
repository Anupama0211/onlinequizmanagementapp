package com.epam.userinterface.loginui;

import com.epam.services.userservices.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class PlayerLogin implements Login {
    private static final Logger LOGGER = LogManager.getLogger(PlayerLogin.class);

    @Override
    public void perform( Scanner scanner, UserService userService) {
        LOGGER.info("Enter Username ");
        String username = scanner.nextLine();
        LOGGER.info("Enter Password");
        String password = scanner.nextLine();
        if (userService.perform(username, password)) {
            LOGGER.info("Login Successful");
            LOGGER.info("PLAYER FUNCTIONALITIES NOT DEFINED");
        } else {
            LOGGER.warn("Invalid Username or Password!!!Try Again");
        }

    }
}
