package com.epam.userinterface.questionlibraryui.questionoperationsui;

import com.epam.entities.Question;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuestionService;
import com.epam.userinterface.GetIdUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.RollbackException;
import java.util.List;

@Component
public class RemoveQuestionsUI implements QuestionOperationsUI {

    private static final Logger LOGGER = LogManager.getLogger(RemoveQuestionsUI.class);

    @Autowired
    GetIdUI getIdUI;
    @Autowired
    QuestionService questionService;
    @Autowired
    PrintQuestionsUI printQuestionsUI;

    @Override
    public void perform() {

        try {
            List<Question> questions = questionService.getAllQuestions();
            LOGGER.info("Following are the questions in the Library");
            printQuestionsUI.perform();
            while (true) {
                try {
                    int questionID = getIdUI.getId("Question ID");
                    Question questionToBeRemoved = questionService.findQuestion(questions, questionID);
                    questionService.removeQuestion(questionToBeRemoved);
                    LOGGER.info("Question is Removed!");
                    break;
                } catch (InvalidIDException e) {
                    LOGGER.warn(e.getMessage());
                }
            }
        } catch (EmptyLibraryException e) {
            LOGGER.warn(e.getMessage());
        } catch (RollbackException e) {
            LOGGER.warn("Question cannot be deleted as it is used in a quiz!!");
        }
    }
}

