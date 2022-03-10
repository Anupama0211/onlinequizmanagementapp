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
import java.util.Optional;

@Component
public class PrintAQuizUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(PrintAQuizUI.class);
    @Autowired
    GetIdUI getIdUI;

    @Override
    public void perform(QuizService quizService) {
        try {
            List<Quiz> quizzes = quizService.getAllQuizzes();
            int quizId = 0;
            List<String> quizTitles = quizService.viewQuizTitles(quizzes);
            quizTitles.forEach(LOGGER::info);
            while (true) {
                quizId = getIdUI.getId("Quiz ID");
                Quiz quiz = quizService.findQuiz(quizzes, quizId);
                LOGGER.info(quiz);
                break;
            }
        } catch (InvalidIDException | EmptyLibraryException e) {
           LOGGER.warn(e.getMessage());
        }
    }
}

