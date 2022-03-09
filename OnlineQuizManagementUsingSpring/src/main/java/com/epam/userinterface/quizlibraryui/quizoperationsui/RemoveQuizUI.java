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
public class RemoveQuizUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(RemoveQuizUI.class);
    @Autowired
    GetIdUI getIdUI;

    @Override
    public void perform(QuizService quizService) {
        Optional<List<Quiz>> quizzesOptional = quizService.getAllQuizzes();
        if (quizzesOptional.isPresent()) {
            List<String> quizTitles = quizService.viewQuizTitles(quizzesOptional.get());
            quizTitles.forEach(LOGGER::info);
            while (true) {
                int quizId = getIdUI.getId("Quiz ID");
                if (quizService.deleteQuiz(quizId)) {
                    LOGGER.info("Quiz Deleted!!!!");
                    break;
                } else {
                    LOGGER.info("Wrong quiz ID");
                }
            }
        } else {
            LOGGER.info("Quiz is not present!!");
        }
    }

}
