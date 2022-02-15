package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;
import com.epam.userinterface.GetQuestionIndex;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModifyQuestion implements QuestionLibraryService {
    private static final Logger LOGGER= LogManager.getLogger(ModifyQuestion.class);
    public void perform() {
        if (QuestionsDatabase.size() > 0) {
           LOGGER.info("Following are the questions in the Library");
            LOGGER.info(QuestionsDatabase.getQuestions());
            int questionIndex = GetQuestionIndex.get(QuestionsDatabase.getQuestions());
            Question question = QuestionGeneratorUI.createAQuestion();
            QuestionsDatabase.modifyQuestion(question, questionIndex - 1);
            LOGGER.info("Question Modified!");
        } else {
         LOGGER.warn("The question library is empty");
        }
    }
}
