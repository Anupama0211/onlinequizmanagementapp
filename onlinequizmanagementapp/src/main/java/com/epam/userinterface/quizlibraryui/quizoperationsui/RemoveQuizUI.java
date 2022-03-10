package com.epam.userinterface.quizlibraryui.quizoperationsui;

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


@Component
public class RemoveQuizUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(RemoveQuizUI.class);
    @Autowired
    GetIdUI getIdUI;
    @Autowired
    QuizService quizService;
    @Override
    public void perform() {
        try {
            List<Quiz> quizzes = quizService.getAllQuizzes();
            List<String> quizTitles = quizService.viewQuizTitles(quizzes);
            quizTitles.forEach(LOGGER::info);
            while (true) {
                if (removeQuiz(quizService, quizzes)) break;
            }
        } catch (EmptyLibraryException e) {
           LOGGER.warn(e.getMessage());
        }
    }

    private boolean removeQuiz(QuizService quizService, List<Quiz> quizzes) {
        try {
            int quizId = getIdUI.getId("Quiz ID");
            Quiz quiz = quizService.findQuiz(quizzes, quizId);
            quizService.deleteQuiz(quiz);
            LOGGER.info("Quiz Deleted!!!!");
            return true;
        } catch (InvalidIDException e) {
           LOGGER.warn(e.getMessage());
        }
        return false;
    }
}
