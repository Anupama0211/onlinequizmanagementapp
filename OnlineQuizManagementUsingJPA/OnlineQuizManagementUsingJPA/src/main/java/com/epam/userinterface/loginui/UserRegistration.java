package com.epam.userinterface.loginui;


import com.epam.entities.User;
import com.epam.services.UserService;
import com.epam.userinterface.AdminPortalUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class UserRegistration implements Login {
    private static final Logger LOGGER = LogManager.getLogger(UserRegistration.class);

    @Override
    public void perform(Scanner scanner, UserService userService, int choice) {
        LOGGER.info("Enter Username ");
        String username = scanner.nextLine();
        LOGGER.info("Enter Password");
        String password = scanner.nextLine();
        if (choice == 3) {

            User user = new User(username, password, "ADMIN");
            if (userService.registerUser(user)) {
                LOGGER.info("Registration Successful");
                new AdminPortalUI().goToTheLibraries(scanner);
            } else {
                LOGGER.info("User already exists!!!");
            }

        } else {

            User user = new User(username, password, "PLAYER");
            if (userService.registerUser(user)) {
                LOGGER.info("Registration Successful");
                LOGGER.info("PLAYER FUNCTIONALITIES NOT DEFINED!");
            } else {
                LOGGER.info("User already exists!!!");
            }

        }
    }
}