package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.dao.QuestionDAO;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.QuestionService;
import com.epam.services.QuizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CreateAndAddQuizFromLibraryUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(CreateAndAddQuizFromLibraryUI.class);

    public int getId(String idFor) {
        Scanner scanner = new Scanner(System.in);
        int id;
        while (true) {
            try {
                LOGGER.info("Enter  {}", idFor);
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                LOGGER.info("Enter a valid  ID");
            }
        }
        return id;
    }

    public int getNoOfQuestions(List<Question> questions, Scanner scanner) {
        int noOfQuestions;

        while (true) {
            try {
                LOGGER.info("How many questions you want to add in the quiz?");
                noOfQuestions = Integer.parseInt(scanner.nextLine());
                if (noOfQuestions <= questions.size()) {
                    break;
                } else {
                    LOGGER.info("There are only {} questions in the library", questions.size());
                }
            } catch (NumberFormatException e) {
                LOGGER.info("Enter a valid number");
            }
        }
        return noOfQuestions;
    }

    @Override
    public void perform(QuizService quizService) {
        Scanner scanner = new Scanner(System.in);
        Quiz quiz = new Quiz();
        LOGGER.info("ENTER THE QUIZ TITLE");
        quiz.setTitle(scanner.nextLine());
        quiz.setQuestions(new HashSet<>());
        int questionId;
        int noOfQuestions;

        QuestionService questionService = new QuestionService(new QuestionDAO());

        List<Question> questions = questionService.readQuestions();
        if (questions.isEmpty()) {
            LOGGER.info("QuestionLibrary is Empty");
        } else {
            noOfQuestions = getNoOfQuestions(questions, scanner);
            LOGGER.info("These are the questions in the question library");
            questions.forEach(LOGGER::info);
            LOGGER.info("Enter the Question ID you want to add in the Quiz");
            int i = 0;
            while (i < noOfQuestions) {
                questionId = getId("Question ID");
                Optional<Question> question = questionService.findQuestion(questionId);
                if (question.isPresent()) {
                    quiz.getQuestions().add(question.get());
                    i++;
                } else {
                    LOGGER.info("Wrong Question ID");
                }
            }
            quizService.insertQuiz(quiz);
            LOGGER.info("Quiz Created!");
        }
    }
}
