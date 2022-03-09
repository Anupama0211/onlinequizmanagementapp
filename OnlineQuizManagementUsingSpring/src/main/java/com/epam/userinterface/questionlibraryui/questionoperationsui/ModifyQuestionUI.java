package com.epam.userinterface.questionlibraryui.questionoperationsui;


import com.epam.entities.Question;
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


    @Override
    public void perform(QuestionService questionService) {
        Optional<List<Question>> questionsOptional = questionService.getAllQuestions();
        if (questionsOptional.isPresent()) {
            questionsOptional.get().forEach(LOGGER::info);
            while (true) {
                int questionId = getIdUI.getId("Question ID");
                if (questionService.findQuestion(questionsOptional.get(), questionId).isPresent()) {
                    LOGGER.info("Enter the modified question");
                    Question question = new QuestionGeneratorUI().createAQuestion();
                    questionService.modifyQuestion(questionId, question);
                    LOGGER.info("Question Modified");
                    break;
                } else {
                    LOGGER.warn("Enter a valid Question ID");
                }
            }
        } else {
            LOGGER.warn("The question library is empty");
        }
    }
}
