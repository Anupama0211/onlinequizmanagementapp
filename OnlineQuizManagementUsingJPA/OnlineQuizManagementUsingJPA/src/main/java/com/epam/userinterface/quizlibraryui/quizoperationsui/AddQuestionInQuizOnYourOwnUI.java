package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.services.QuizService;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class AddQuestionInQuizOnYourOwnUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(AddQuestionInQuizOnYourOwnUI.class);

    public int getQuizId() {
        Scanner scanner = new Scanner(System.in);
        int quizId;
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
        int quizId;
        if (quizTitles.isEmpty()) {
            LOGGER.warn("The Quiz library is empty!!!Make a new Quiz");
        } else {
            quizTitles.forEach(LOGGER::info);
            quizId = getQuizId();
            Question question = new QuestionGeneratorUI().createAQuestion();
            while (true) {
                if (quizService.addQuetsionInQuizOnYourOwn(quizId, question)) {
                    LOGGER.info("Question added in the quiz");
                    break;
                } else {
                    LOGGER.info("Wrong quizID");
                    quizId = getQuizId();
                }
            }
        }
    }
}
