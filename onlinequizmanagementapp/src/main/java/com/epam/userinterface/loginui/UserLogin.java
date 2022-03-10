package com.epam.userinterface.loginui;

import com.epam.exceptions.UserNotFoundException;
import com.epam.services.UserService;
import com.epam.userinterface.AdminPortalUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserLogin implements Login {

    private static final Logger LOGGER = LogManager.getLogger(UserLogin.class);
    @Autowired
    AdminPortalUI adminPortalUI;

    @Override
    public void perform(Scanner scanner, UserService userService, int choice) {
        LOGGER.info("Enter Username ");
        String username = scanner.nextLine();
        LOGGER.info("Enter Password");
        String password = scanner.nextLine();
        try {
            if (userService.validateCredentials(username, password, choice)) {
                LOGGER.info("Login Successful");
                if (choice == 1) {
                    adminPortalUI.goToTheLibraries(scanner);
                } else {
                    LOGGER.info("PLAYER FUNCTIONALITIES NOT DEFINED");
                }
            } else {
                LOGGER.warn("Invalid Username or Password!!!Try Again");
            }
        } catch (UserNotFoundException e) {
            LOGGER.info(e.getMessage());
        }

    }
}
