package com.epam.userinterface.loginui;

import com.epam.services.userservices.UserService;
import com.epam.userinterface.AdminPortalUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class AdminRegistration implements Login {
    private static final Logger LOGGER = LogManager.getLogger(AdminRegistration.class);

    @Override
    public void perform(Scanner scanner, UserService userService) {
        LOGGER.info("Enter Username ");
        String username = scanner.nextLine();
        LOGGER.info("Enter Password");
        String password = scanner.nextLine();
        if (userService.perform(username, password)) {
            LOGGER.info("Registration Successful");
            new AdminPortalUI().goToTheLibraries(scanner);
        } else {
            LOGGER.warn("Username already exists , try another username!");
        }
    }
}
