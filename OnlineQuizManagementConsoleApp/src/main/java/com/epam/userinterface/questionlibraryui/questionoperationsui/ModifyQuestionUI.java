package com.epam.userinterface.questionlibraryui.questionoperationsui;


import com.epam.entities.Question;
import com.epam.services.QuestionService;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ModifyQuestionUI implements QuestionOperationsUI {

    private static final Logger LOGGER = LogManager.getLogger(ModifyQuestionUI.class);

    public int getQuestionID(Scanner scanner) {
        int questionId;
        while (true) {
            LOGGER.info("Enter the Question ID of the question you want to modify");
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
        Scanner scanner = new Scanner(System.in);
        if (questionService.readQuestions().size() > 0) {
            questionService.readQuestions().forEach(LOGGER::info);

            LOGGER.info("Enter the Question ID of the question you want to modify");
            int questionId = getQuestionID(scanner);
            while (true) {
                if (questionService.findQuestion(questionId).isPresent()) {
                    LOGGER.info("Enter the modified question");
                    Question question = new QuestionGeneratorUI().createAQuestion();
                    questionService.modifyQuestion(questionId, question);
                    break;
                } else {
                    LOGGER.warn("Enter a valid Question ID");
                    getQuestionID(scanner);
                }
            }
        } else {
            LOGGER.warn("The question library is empty");
        }
    }
}
