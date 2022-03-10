package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
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
        while (true) {
            try {
                List<Quiz> quizzes = quizService.getAllQuizzes();
                quizService.viewQuizTitles(quizzes).forEach(LOGGER::info);

                int quizId = getIdUI.getId("Quiz ID");
                Quiz quiz = quizService.findQuiz(quizzes, quizId);
                LOGGER.info(quiz);
                int questionId = getIdUI.getId("Question ID");
                Question question = quizService.getQuestionInAQuiz(quiz, questionId);
                quizService.deleteQuestionInQuiz(quiz, question);
                LOGGER.info("Question Deleted");
                break;
            } catch (EmptyLibraryException | InvalidIDException e) {
                LOGGER.warn(e.getMessage());
            }
        }

//        Optional<
//                int quizId = 0;
//        int questionId = 0;
//        if (quizzesOptional.isPresent()) {
//            List<Quiz> quizzes = quizzesOptional.get();
//
//
//        }
//    } else
//
//    {
//        LOGGER.warn("The Quiz library is empty!!!Make a new Quiz");
//    }
    }
}
