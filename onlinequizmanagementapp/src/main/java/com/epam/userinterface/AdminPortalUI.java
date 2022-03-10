package com.epam.userinterface;


import com.epam.userinterface.questionlibraryui.QuestionLibraryPortal;
import com.epam.userinterface.quizlibraryui.QuizLibraryPortal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AdminPortalUI {
    private static final Logger LOGGER = LogManager.getLogger(AdminPortalUI.class);
    @Autowired
    QuestionLibraryPortal questionLibraryPortal;
    @Autowired
    QuizLibraryPortal quizLibraryPortal;

    public void displayOptions() {
        LOGGER.info("Welcome to the Admin Portal!!!");
        LOGGER.info("1.Questions Library");
        LOGGER.info("2.Quiz Library");
        LOGGER.info("3.Exit from the Admin portal");
        LOGGER.info("Enter your choice");

    }

    public void goToTheLibraries(Scanner scanner) {
        do {
            try {
                displayOptions();
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    questionLibraryPortal.goToQuestionsLibrary(scanner);
                } else if (choice == 2) {
                    quizLibraryPortal.modifyTheQuizLibrary(scanner);
                } else if (choice == 3) {
                    break;
                } else {
                    LOGGER.info("Enter a valid choice!!!");
                }
            } catch (NumberFormatException e) {
                LOGGER.error("Enter a valid choice!!!");
            }
        } while (true);
    }
}