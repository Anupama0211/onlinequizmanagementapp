package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateAndAddQuestion implements QuestionLibraryService {

    private static final Logger LOGGER= LogManager.getLogger(CreateAndAddQuestion.class);
    @Override
    public void perform() {
        Question question = QuestionGeneratorUI.createAQuestion();
        QuestionsDatabase.addQuestion(question);
       LOGGER.info("Question Added!!!");
    }
}
