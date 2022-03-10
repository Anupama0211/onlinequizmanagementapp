package com.epam.userinterface.loginui;


import com.epam.entities.User;
import com.epam.services.UserService;
import com.epam.userinterface.AdminPortalUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class UserRegistration implements Login {
    private static final Logger LOGGER = LogManager.getLogger(UserRegistration.class);
    @Autowired
    AdminPortalUI adminPortalUI;
    @Override
    public void perform(Scanner scanner, UserService userService, int choice) {

        User user=new User();
        LOGGER.info("Enter Username ");
        String username = scanner.nextLine();
        LOGGER.info("Enter Password");
        String password = scanner.nextLine();
        user.setUserName(username);
        user.setPassword(password);
        if (choice == 3) {
            user.setType("ADMIN");
            if (userService.registerUser(user)) {
                LOGGER.info("Registration Successful");
                adminPortalUI.goToTheLibraries(scanner);
            } else {
                LOGGER.info("User already exists!!!");
            }

        } else {
            user.setType("PLAYER");
            if (userService.registerUser(user)) {
                LOGGER.info("Registration Successful");
                LOGGER.info("PLAYER FUNCTIONALITIES NOT DEFINED!");
            } else {
                LOGGER.info("User already exists!!!");
            }

        }
    }
}