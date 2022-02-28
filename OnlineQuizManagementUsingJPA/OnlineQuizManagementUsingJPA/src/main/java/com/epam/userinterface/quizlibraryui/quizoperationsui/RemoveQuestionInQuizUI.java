package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Quiz;
import com.epam.services.QuizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RemoveQuestionInQuizUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(RemoveQuestionInQuizUI.class);

    public int getId(String idFor) {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        while (true) {
            try {
                LOGGER.info("Enter the {} ", idFor);
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                LOGGER.info("Enter a valid  ID");
            }
        }
        return id;
    }

    @Override
    public void perform(QuizService quizService) {
        List<String> quizTitles = quizService.viewQuizTitles();
        int quizId = 0;
        int questionId = 0;
        if (quizTitles.isEmpty()) {
            LOGGER.warn("The Quiz library is empty!!!Make a new Quiz");
        } else {
            quizTitles.forEach(LOGGER::info);
            quizId = getId("Quiz ID");
            Optional<Quiz> quizOptional = quizService.viewAQuiz(quizId);
            while (true) {
                if (quizOptional.isPresent()) {
                    LOGGER.info(quizOptional.get());
                    questionId = getId("Question ID");
                    if (quizService.deleteQuestionInQuiz(quizId, questionId)) {
                        LOGGER.info("Question deleted in the quiz");
                        break;
                    } else {
                        LOGGER.info("Wrong Question ID");
                    }
                } else {
                    LOGGER.info("Wrong Quiz ID");
                    quizId = getId("Quiz ID");
                }
            }
        }
    }
}
