package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.dao.QuestionDAO;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.QuestionService;
import com.epam.services.QuizService;
import com.epam.userinterface.GetIdUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CreateAndAddQuizFromLibraryUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(CreateAndAddQuizFromLibraryUI.class);

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
        QuestionService questionService = new QuestionService(new QuestionDAO());
        Optional<List<Question>> questionsOptional = questionService.getAllQuestions();
        if (questionsOptional.isPresent()) {
            List<Question> questions = questionsOptional.get();
            LOGGER.info("These are the questions in the question library");
            questions.forEach(LOGGER::info);
            Quiz newquiz = addQuestionsInQuiz(quiz, questionService, questions);
            quizService.insertQuiz(newquiz);
            LOGGER.info("Quiz Created!!!");
        } else {
            LOGGER.info("Question Library is empty!!");
        }
    }

    private Quiz addQuestionsInQuiz(Quiz quiz, QuestionService questionService, List<Question> questions) {
        Scanner scanner = new Scanner(System.in);
        int noOfQuestions = getNoOfQuestions(questions, scanner);
        GetIdUI getIdUI = new GetIdUI();
        int i = 0;
        while (i < noOfQuestions) {
            int questionId = getIdUI.getId("Question ID");
            Optional<Question> questionOptional = questionService.findQuestion(questions, questionId);
            if (questionOptional.isPresent()) {
                quiz.getQuestions().add(questionOptional.get());
                i++;
            } else {
                LOGGER.info("Wrong Question ID");
            }
        }
        return quiz;
    }
}