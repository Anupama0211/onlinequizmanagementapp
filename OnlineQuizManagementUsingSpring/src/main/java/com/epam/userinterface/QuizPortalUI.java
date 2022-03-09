package com.epam.userinterface;

import com.epam.GetApplicationContext;
import com.epam.services.UserService;
import com.epam.userinterface.loginui.Login;
import com.epam.userinterface.loginui.LoginFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.util.Optional;
import java.util.Scanner;

public class QuizPortalUI {

    private static final Logger LOGGER = LogManager.getLogger(QuizPortalUI.class);


    public void displayOptions() {
        LOGGER.info("-----------------WELCOME TO THE QUIZ PORTAL-----------------");
        LOGGER.info("1.Login as Admin");
        LOGGER.info("2.Login as Player");
        LOGGER.info("3.Register as Admin");
        LOGGER.info("4.Register as Player");
        LOGGER.info("5.Exit");
    }

     public void launch(Scanner scanner) {
        ApplicationContext context= GetApplicationContext.get();
        int choice = 0;
        while (true) {
            try {
                displayOptions();
                choice = Integer.parseInt(scanner.nextLine());
                Optional<Login> login = new LoginFactory().getLogin(choice);
                UserService userService=context.getBean(UserService.class);
                if (login.isPresent()) {
                    login.get().perform(scanner,userService,choice);
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



