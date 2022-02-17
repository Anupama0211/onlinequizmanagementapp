package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PrintQuestions implements QuestionLibraryService {
    private static final Logger LOGGER= LogManager.getLogger(PrintQuestions.class);
    public void perform() {
        if (QuestionsDatabase.size() > 0) {
            List<Question> questions = QuestionsDatabase.getQuestions();
            int index = 1;
            for (Question question : questions) {
               LOGGER.info( "{} {}",index,question);
                index++;
            }
        } else {
            LOGGER.warn("The question library is empty!!!");
        }
    }
}
