package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuestionService;
import com.epam.services.QuizService;
import com.epam.userinterface.GetIdUI;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class CreateAndAddQuizFromLibraryUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(CreateAndAddQuizFromLibraryUI.class);

    @Autowired
    GetIdUI getIdUI;
    @Autowired
    QuestionService questionService;

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
        try {
            Scanner scanner = new Scanner(System.in);
            List<Question> questions = questionService.getAllQuestions();
            LOGGER.info("These are the questions in the question library");
            questions.forEach(LOGGER::info);
            Quiz quiz = new Quiz();
            LOGGER.info("ENTER THE QUIZ TITLE");
            quiz.setTitle(scanner.nextLine());
            quiz.setQuestions(new HashSet<>());
            Quiz newquiz = addQuestionsInQuiz(quiz, questions);
            quizService.insertQuiz(newquiz);
            LOGGER.info("Quiz Created!!!");
        } catch (EmptyLibraryException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private Quiz addQuestionsInQuiz(Quiz quiz, List<Question> questions) {
        Scanner scanner = new Scanner(System.in);
        int noOfQuestions = getNoOfQuestions(questions, scanner);
        int i = 0;
        while (i <= noOfQuestions) {
            try {
                int questionId = getIdUI.getId("Question ID");
                Question question = questionService.findQuestion(questions, questionId);
                quiz.getQuestions().add(question);
                i++;
            } catch (InvalidIDException e) {
                LOGGER.warn(e.getMessage());
                i--;
            }
        }
        return quiz;
    }
}
