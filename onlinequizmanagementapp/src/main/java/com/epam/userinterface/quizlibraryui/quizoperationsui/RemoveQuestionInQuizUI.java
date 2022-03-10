package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.QuizService;
import com.epam.userinterface.GetIdUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RemoveQuestionInQuizUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(RemoveQuestionInQuizUI.class);

    @Autowired
    GetIdUI getIdUI;

    @Override
    public void perform(QuizService quizService) {
        Optional<List<Quiz>> quizzesOptional = quizService.getAllQuizzes();
        int quizId = 0;
        int questionId = 0;
        if (quizzesOptional.isPresent()) {
            List<Quiz> quizzes = quizzesOptional.get();
            quizService.viewQuizTitles(quizzes).forEach(LOGGER::info);
            while (true) {
                quizId = getIdUI.getId("Quiz ID");
                Optional<Quiz> quizOptional = quizService.findQuiz(quizzes, quizId);
                if (quizOptional.isPresent()) {
                    Quiz quiz = quizOptional.get();
                    LOGGER.info(quiz);
                    questionId = getIdUI.getId("Question ID");
                    Optional<Question> questionOptional = quizService.getQuestionInAQuiz(quizOptional, questionId);
                    if (quizService.deleteQuestionInQuiz(quiz, questionOptional)) {
                        LOGGER.info("Question Deleted");
                        break;
                    } else {
                        LOGGER.info("Wrong Question ID ");
                    }
                } else {
                    LOGGER.info("Wrong Quiz ID");
                }
            }
        } else {
            LOGGER.warn("The Quiz library is empty!!!Make a new Quiz");
        }
    }
}
