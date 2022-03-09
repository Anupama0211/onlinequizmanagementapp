package com.epam.userinterface.questionlibraryui.questionoperationsui;

import com.epam.services.QuestionService;
import com.epam.userinterface.GetIdUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveQuestionsUI implements QuestionOperationsUI {

    private static final Logger LOGGER = LogManager.getLogger(RemoveQuestionsUI.class);

    @Autowired
    GetIdUI getIdUI;

    @Override
    public void perform(QuestionService questionService) {
        if (questionService.getAllQuestions().isEmpty()) {
            LOGGER.warn("Question Library is empty!!!");
        } else {
            LOGGER.info("Following are the questions in the Library");
            new PrintQuestionsUI().perform(questionService);
            do {
                int questionID = getIdUI.getId("Question ID");
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
