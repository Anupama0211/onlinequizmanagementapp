package com.epam.userinterface.questionlibraryui.questionoperationsui;


import com.epam.entities.Question;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuestionService;
import com.epam.userinterface.GetIdUI;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class ModifyQuestionUI implements QuestionOperationsUI {

    private static final Logger LOGGER = LogManager.getLogger(ModifyQuestionUI.class);
    @Autowired
    GetIdUI getIdUI;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionGeneratorUI questionGeneratorUI;

    @Override
    public void perform() {
        while (true) {
            try {
                List<Question> questions = questionService.getAllQuestions();
                questions.forEach(LOGGER::info);
                int questionId = getIdUI.getId("Question ID");
                Question oldQuestion = questionService.findQuestion(questions, questionId);
                LOGGER.info("Enter the modified question");
                Question newQuestion = questionGeneratorUI.createAQuestion();
                questionService.modifyQuestion(oldQuestion, newQuestion);
                LOGGER.info("Question Modified");
                break;
            } catch (EmptyLibraryException | InvalidIDException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }
}
