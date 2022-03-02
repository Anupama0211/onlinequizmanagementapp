package com.epam.userinterface.questionlibraryui;

import com.epam.dao.GetManager;
import com.epam.dao.QuestionDAO;
import com.epam.services.QuestionService;
import com.epam.userinterface.questionlibraryui.questionoperationsui.QuestionOperationsUI;
import com.epam.userinterface.questionlibraryui.questionoperationsui.QuestionOperationsUIFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.Scanner;

public class QuestionLibraryPortal {
    private static final Logger LOGGER = LogManager.getLogger(QuestionLibraryPortal.class);

    public void displayOptions() {
        LOGGER.info("Welcome to the Questions Library!!!");
        LOGGER.info("1.Create and add a Question to the library");
        LOGGER.info("2.Modify a question from the library");
        LOGGER.info("3.Remove a question from the library");
        LOGGER.info("4.View Questions in the Library");
        LOGGER.info("5.Exit Questions Portal");
        LOGGER.info("Enter your choice--");
    }

    public void goToQuestionsLibrary(Scanner scanner) {
        String display = "Enter a valid choice!!!";
        EntityManager entityManager= GetManager.getEntityManger();
        QuestionDAO questionDAO=new QuestionDAO(entityManager);
        QuestionService questionService=new QuestionService(questionDAO);
        do {
            try {
                displayOptions();
                int choice = Integer.parseInt(scanner.nextLine());
                Optional<QuestionOperationsUI> questionOperationsUI = new QuestionOperationsUIFactory().getQuestionOperations(choice);
                if (questionOperationsUI.isPresent()) {
                    questionOperationsUI.get().perform(questionService);
                } else if (choice == 5) {
                    break;
                } else {
                    LOGGER.info(display);
                }
            } catch (NumberFormatException e) {
                LOGGER.warn(display);
            }
        } while (true);
    }
}

