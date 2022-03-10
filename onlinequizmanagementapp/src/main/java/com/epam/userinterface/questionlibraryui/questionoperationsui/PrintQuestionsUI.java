package com.epam.userinterface.questionlibraryui.questionoperationsui;

import com.epam.entities.Question;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.services.QuestionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PrintQuestionsUI implements QuestionOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(PrintQuestionsUI.class);
    @Autowired
    QuestionService questionService;

    @Override
    public void perform() {
        try {
            List<Question> questions= questionService.getAllQuestions();
            int index = 1;
            for (Question question : questions) {
                LOGGER.info("{} {}", index, question);
                index++;
            }
        } catch (EmptyLibraryException e) {
            LOGGER.warn(e.getMessage());

        }
    }
}
