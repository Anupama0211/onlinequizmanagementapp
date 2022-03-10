package com.epam.userinterface.quizlibraryui.quizoperationsui;


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
public class PrintAQuizUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(PrintAQuizUI.class);
    @Autowired
    GetIdUI getIdUI;
    @Override
    public void perform(QuizService quizService) {
        Optional<List<Quiz>> optionalQuizzes = quizService.getAllQuizzes();
        int quizId = 0;
        if (optionalQuizzes.isPresent()) {
            List<String> quizTitles = quizService.viewQuizTitles(optionalQuizzes.get());
            quizTitles.forEach(LOGGER::info);
            while (true) {
                quizId = getIdUI.getId("Quiz ID");
                Optional<Quiz> quizOptional = quizService.findQuiz(optionalQuizzes.get(), quizId);
                if (quizOptional.isPresent()) {
                    LOGGER.info(quizOptional.get());
                    break;
                } else {
                    LOGGER.info("Enter a valid Quiz ID");
                }
            }
        } else {
            LOGGER.warn("Quiz Library is Empty");
        }
    }
}

