package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.services.QuizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class RemoveQuizUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(RemoveQuizUI.class);

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
            LOGGER.warn("Quiz Library is empty");
        } else {
            quizTitles.forEach(LOGGER::info);
            while (true) {
                quizId = getQuizId();
                if (quizService.deleteQuiz(quizId)) {
                    LOGGER.info("Quiz Deleted!!!!");
                    break;
                } else {
                    LOGGER.info("Wrong quiz ID");
                }
            }
        }
    }

}
