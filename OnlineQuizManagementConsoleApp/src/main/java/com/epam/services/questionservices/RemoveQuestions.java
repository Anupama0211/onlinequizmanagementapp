package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;
import com.epam.userinterface.GetQuestionIndex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveQuestions implements QuestionLibraryService {

    private static final Logger LOGGER= LogManager.getLogger(RemoveQuestions.class);
    public void perform() {
        if (QuestionsDatabase.size() > 0) {
            LOGGER.info("Following are the questions in the Library");
            new PrintQuestions().perform();
            int questionIndex = GetQuestionIndex.get(QuestionsDatabase.getQuestions());
            LOGGER.info("The following question is removed.....");
            LOGGER.info(QuestionsDatabase.removeQuestion(questionIndex - 1));
        } else {
            LOGGER.warn("Question Library is empty!!!");
        }
    }
}
