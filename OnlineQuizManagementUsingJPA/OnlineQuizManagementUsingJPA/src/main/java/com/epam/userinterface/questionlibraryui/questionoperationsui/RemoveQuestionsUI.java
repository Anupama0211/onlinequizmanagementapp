package com.epam.userinterface.questionlibraryui.questionoperationsui;

import com.epam.services.QuestionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class RemoveQuestionsUI implements QuestionOperationsUI {

    private static final Logger LOGGER = LogManager.getLogger(RemoveQuestionsUI.class);

    public int getQuestionID() {
        Scanner scanner = new Scanner(System.in);
        int questionId;
        while (true) {
            LOGGER.info("Enter the Question ID of the question you want to remove");
            try {
                questionId = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                LOGGER.error("Enter a valid Question ID");
            }
        }
        return questionId;
    }

    @Override
    public void perform(QuestionService questionService) {
        if (questionService.getAllQuestions().isEmpty()) {
            LOGGER.warn("Question Library is empty!!!");
        } else {
            LOGGER.info("Following are the questions in the Library");
            new PrintQuestionsUI().perform(questionService);
            do {
                int questionID = getQuestionID();
                int status = questionService.removeQuestion(questionID);
                if (status == 0) {
                    LOGGER.warn("Enter the correct Question Id");
                } else {
                    if (status == 1) {
                        LOGGER.info("The question is removed.....");
                    } else {
                        LOGGER.info("Question is used in the quiz!! PLease delete the question in the quiz first!!");
                    }
                    break;
                }
            } while (true);
        }
    }
}
