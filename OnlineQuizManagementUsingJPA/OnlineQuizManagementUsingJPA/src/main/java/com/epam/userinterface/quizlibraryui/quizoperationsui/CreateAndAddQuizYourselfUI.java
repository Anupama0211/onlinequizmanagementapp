package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.QuizService;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Scanner;

public class CreateAndAddQuizYourselfUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(CreateAndAddQuizYourselfUI.class);

    @Override
    public void perform(QuizService quizService) {
        Scanner scanner = new Scanner(System.in);
        Quiz quiz = new Quiz();
        LOGGER.info("ENTER THE QUIZ TITLE");
        quiz.setTitle(scanner.nextLine());
        quiz.setQuestions(new HashSet<>());

        int noOfQuestions;
        while (true) {
            try {
                LOGGER.info("How many questions do you want to add in the quiz?");
                noOfQuestions = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                LOGGER.info("Enter a valid number");
            }
        }
        int quizId = quizService.insertQuiz(quiz);
        for (int i = 0; i < noOfQuestions; i++) {
            Question question = new QuestionGeneratorUI().createAQuestion();
            quizService.addQuetsionInQuizOnYourOwn(quizId, question);
        }
        LOGGER.info("Quiz Created");
    }
}

