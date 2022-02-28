package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.dao.QuestionDAO;
import com.epam.entities.Question;
import com.epam.services.QuestionService;
import com.epam.services.QuizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class AddQuestionInQuizFromQuestionLibrary implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(AddQuestionInQuizFromQuestionLibrary.class);


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
        int quizId;
        int questionId;
        if (quizTitles.isEmpty()) {
            LOGGER.warn("The Quiz library is empty!!!Make a new Quiz");
        } else {
            quizTitles.forEach(LOGGER::info);
            quizId = getId("Quiz Id");
            List<Question> questions = new QuestionService(new QuestionDAO()).readQuestions();
            if (questions.isEmpty()) {
                LOGGER.info("QuestionLibrary is Empty");
            } else {
                LOGGER.info("These are the questions in the question library");
                questions.forEach(LOGGER::info);
                LOGGER.info("Enter the Question ID you want to add in the Quiz");
                questionId = getId("Question Id");
                while (true) {
                    if (quizService.addQuestionFromQuestionLibrary(quizId, questionId)) {
                        LOGGER.info("Question Added In the Quiz");
                        break;
                    } else {
                        LOGGER.info("Wrong question Id OR quiz Id");
                        quizId = getId("Quiz Id");
                        questionId = getId("Question Id");
                    }
                }
            }
        }
    }
}
