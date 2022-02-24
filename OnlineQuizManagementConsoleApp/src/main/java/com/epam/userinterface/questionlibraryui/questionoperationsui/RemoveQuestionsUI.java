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
        if (questionService.readQuestions().size() > 0) {
            LOGGER.info("Following are the questions in the Library");
            new PrintQuestionsUI().perform(questionService);
            while (true) {
                int questionID = getQuestionID();
                if (questionService.removeQuestion(questionID).isPresent()) {
                    LOGGER.info("The question is removed.....");
                    break;
                } else {
                    LOGGER.warn("Enter the correct Question Id");
                }
            }
        } else {
            LOGGER.warn("Question Library is empty!!!");
        }
    }
}
