package com.epam.userinterface.loginui;


import com.epam.GetApplicationContext;
import com.epam.entities.User;
import com.epam.services.UserService;
import com.epam.userinterface.AdminPortalUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;


import java.util.Scanner;

public class UserRegistration implements Login {
    private static final Logger LOGGER = LogManager.getLogger(UserRegistration.class);

    @Override
    public void perform(Scanner scanner, UserService userService, int choice) {
        ApplicationContext context= GetApplicationContext.get();
        LOGGER.info("Enter Username ");
        String username = scanner.nextLine();
        LOGGER.info("Enter Password");
        String password = scanner.nextLine();
        User user=context.getBean(User.class);
        user.setUserName(username);
        user.setPassword(password);
        if (choice == 3) {
            user.setType("ADMIN");
            if (userService.registerUser(user)) {
                LOGGER.info("Registration Successful");
                AdminPortalUI adminPortalUI=context.getBean(AdminPortalUI.class);
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