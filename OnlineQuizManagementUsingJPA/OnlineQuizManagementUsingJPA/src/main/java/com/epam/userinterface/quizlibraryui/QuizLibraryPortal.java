package com.epam.userinterface.quizlibraryui;


import com.epam.dao.GetManager;
import com.epam.dao.QuizDAO;
import com.epam.services.QuizService;
import com.epam.userinterface.quizlibraryui.quizoperationsui.QuizOperationsUI;
import com.epam.userinterface.quizlibraryui.quizoperationsui.QuizOperationsUIFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.Scanner;

public class QuizLibraryPortal {

    private static final Logger LOGGER = LogManager.getLogger(QuizLibraryPortal.class);


    public void displayOptions() {
        LOGGER.info("Welcome to the Quiz Library!!!");
        LOGGER.info("1.View a Quiz");
        LOGGER.info("2.Create a Quiz On Your Own");
        LOGGER.info("3.Create a Quiz From Question Library");
        LOGGER.info("4.Delete a Quiz");
        LOGGER.info("5.Add a question in an Existing Quiz on your own ");
        LOGGER.info("6.Add a question in an Existing Quiz from Question Library");
        LOGGER.info("7.Delete a question in a Quiz");
        LOGGER.info("8.Exit Quiz Portal");
        LOGGER.info("Enter your choice");
    }

    public void modifyTheQuizLibrary(Scanner scanner) {
        String display = "Enter a valid choice!!!";
        QuizOperationsUIFactory quizOperationsUIFactory = new QuizOperationsUIFactory();
        EntityManager entityManager = GetManager.getEntityManger();
        QuizDAO quizDAO = new QuizDAO(entityManager);
        QuizService quizService = new QuizService(quizDAO);
        do {
            try {
                displayOptions();
                int choice = Integer.parseInt(scanner.nextLine());
                Optional<QuizOperationsUI> quizOperationsUI = quizOperationsUIFactory.getQuizOperations(choice);
                if (quizOperationsUI.isPresent()) {
                    quizOperationsUI.get().perform(quizService);
                } else if (choice == 8) {
                    break;
                } else {
                    LOGGER.info(display);
                }
            } catch (NumberFormatException e) {
                LOGGER.error(display);
            }
        } while (true);
    }

}

