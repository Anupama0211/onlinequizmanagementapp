package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.QuizService;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Scanner;

@Component
public class CreateAndAddQuizYourselfUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(CreateAndAddQuizYourselfUI.class);
    @Autowired
    QuestionGeneratorUI questionGeneratorUI;
    @Autowired
    QuizService quizService;
    @Override
    public void perform() {
        Scanner scanner = new Scanner(System.in);
        Quiz quiz = new Quiz();
        LOGGER.info("ENTER THE QUIZ TITLE");
        quiz.setTitle(scanner.nextLine());
        quiz.setQuestions(new HashSet<>());
        int noOfQuestions = getNoOfQuestions(scanner);
        quiz = quizService.insertQuiz(quiz);
        for (int i = 0; i < noOfQuestions; i++) {
            Question question = questionGeneratorUI.createAQuestion();
            quizService.addQuetsionInQuizOnYourOwn(quiz, question);
        }
        LOGGER.info("Quiz Created");
    }

    private int getNoOfQuestions(Scanner scanner) {
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
        return noOfQuestions;
    }
}

