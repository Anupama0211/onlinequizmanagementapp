package com.epam.userinterface.questionlibraryui.questionoperationsui;

import com.epam.entities.Question;
import com.epam.services.QuestionService;

import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateAndAddQuestionUI implements QuestionOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(CreateAndAddQuestionUI.class);

    @Override
    public void perform(QuestionService questionService) {
        Question question = new QuestionGeneratorUI().createAQuestion();
        questionService.addQuestion(question);
        LOGGER.info("Question added!");
    }
}
