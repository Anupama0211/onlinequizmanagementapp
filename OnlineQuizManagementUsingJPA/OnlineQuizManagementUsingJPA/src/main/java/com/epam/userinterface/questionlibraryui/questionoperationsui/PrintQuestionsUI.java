package com.epam.userinterface.questionlibraryui.questionoperationsui;

import com.epam.entities.Question;
import com.epam.services.QuestionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class PrintQuestionsUI implements QuestionOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(PrintQuestionsUI.class);

    @Override
    public void perform(QuestionService questionService) {
        Optional<List<Question>> questionsOptional = questionService.getAllQuestions();
        if (questionsOptional.isPresent()) {
            List<Question> questions=questionsOptional.get();
            int index = 1;
            for (Question question : questions) {
                LOGGER.info("{} {}", index, question);
                index++;
            }
        } else {
            LOGGER.warn("The question library is empty!!!");

        }
    }
}
