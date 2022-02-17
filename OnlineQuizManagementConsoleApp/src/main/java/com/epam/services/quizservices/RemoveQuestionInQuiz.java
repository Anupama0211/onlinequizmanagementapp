package com.epam.services.quizservices;


import com.epam.database.QuizDatabase;
import com.epam.entities.Quiz;
import com.epam.userinterface.GetQuestionIndex;
import com.epam.userinterface.GetQuizIndex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveQuestionInQuiz implements QuizLibraryService {
    private static final Logger LOGGER = LogManager.getLogger(RemoveQuestionInQuiz.class);

    public void perform() {
        if (QuizDatabase.size() > 0) {
            LOGGER.info(new QuizTitles().get());
            LOGGER.info("In which quiz do you want to delete the question");
            int quizIndex = GetQuizIndex.get();
            Quiz quiz = QuizDatabase.getQuiz(quizIndex - 1).get();
            if (quiz.getQuestionList().isEmpty()) {
                LOGGER.info("Quiz is empty!Add Question in the quiz");
            } else {
                LOGGER.info(quiz);
                int questionNumber = GetQuestionIndex.get(quiz.getQuestionList());
                QuizDatabase.deleteQuestionInAQuiz(quizIndex - 1, questionNumber - 1);
                LOGGER.info("Question deleted in the quiz!!!");
            }
        } else {
            LOGGER.warn("The Quiz library is empty!!!Make a new Quiz");
        }
    }
}
