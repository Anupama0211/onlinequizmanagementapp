package com.epam.userinterface;

import com.epam.services.UserService;
import com.epam.userinterface.loginui.Login;
import com.epam.userinterface.loginui.LoginFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;
@Component
public class QuizPortalUI {

    private static final Logger LOGGER = LogManager.getLogger(QuizPortalUI.class);

    @Autowired
    UserService userService;

    @Autowired
    LoginFactory loginFactory;
    public void displayOptions() {
        LOGGER.info("-----------------WELCOME TO THE QUIZ PORTAL-----------------");
        LOGGER.info("1.Login as Admin");
        LOGGER.info("2.Login as Player");
        LOGGER.info("3.Register as Admin");
        LOGGER.info("4.Register as Player");
        LOGGER.info("5.Exit");
    }

    public void launch(Scanner scanner) {
        int choice = 0;
        while (true) {
            try {
                displayOptions();
                choice = Integer.parseInt(scanner.nextLine());
                Optional<Login> login = loginFactory.getLogin(choice);
                if (login.isPresent()) {
                    login.get().perform(scanner, userService, choice);
                } else if (choice == 5) {
                    LOGGER.info("Exited......");
                    break;
                } else {
                    LOGGER.warn("Enter a valid choice!!");
                }
            } catch (NumberFormatException e) {
                LOGGER.error("Enter a valid choice!!");
            }
        }
    }
}



