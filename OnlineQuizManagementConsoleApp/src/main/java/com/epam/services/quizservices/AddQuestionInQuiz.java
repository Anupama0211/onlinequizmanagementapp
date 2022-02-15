package com.epam.services.quizservices;


import com.epam.database.QuizDatabase;
import com.epam.entities.Question;
import com.epam.userinterface.GetQuizIndex;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddQuestionInQuiz implements QuizLibraryService {
    private static final Logger LOGGER= LogManager.getLogger(AddQuestionInQuiz.class);
    @Override
    public void perform() {
        if (QuizDatabase.size() > 0) {
            LOGGER.info(new QuizTitles().get());
            LOGGER.info("In which quiz do you want to add the question");
            int quizIndex = GetQuizIndex.get();
            Question question = QuestionGeneratorUI.createAQuestion();
            QuizDatabase.addQuestionInAQuiz(quizIndex - 1, question);
            LOGGER.info("Question added in the Quiz!!!");
        } else {
            LOGGER.info("The Quiz library is empty!!!Make a new Quiz");
        }
    }
}
