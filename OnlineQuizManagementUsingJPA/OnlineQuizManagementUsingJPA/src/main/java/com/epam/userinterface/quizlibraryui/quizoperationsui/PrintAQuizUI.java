package com.epam.userinterface.quizlibraryui.quizoperationsui;


import com.epam.entities.Quiz;
import com.epam.services.QuizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PrintAQuizUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(PrintAQuizUI.class);

    public int getQuizId() {
        Scanner scanner = new Scanner(System.in);
        int quizId = 0;
        while (true) {
            try {
                LOGGER.info("Enter the quizID");
                quizId = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                LOGGER.info("Enter a valid Quiz ID");
            }
        }
        return quizId;
    }

    @Override
    public void perform(QuizService quizService) {
        List<String> quizTitles = quizService.viewQuizTitles();
        int quizId = 0;
        if (quizTitles.isEmpty()) {
            LOGGER.warn("Quiz Library is Empty");
        } else {
            quizTitles.forEach(LOGGER::info);
            while (true) {
                quizId = getQuizId();
                Optional<Quiz> quizOptional = quizService.viewAQuiz(quizId);
                if (quizOptional.isPresent()) {
                    LOGGER.info(quizOptional.get());
                    break;
                } else {
                    LOGGER.info("Enter a valid Quiz ID");
                }
            }
        }
    }
}

