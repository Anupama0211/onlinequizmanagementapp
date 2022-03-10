package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuestionService;
import com.epam.services.QuizService;
import com.epam.userinterface.GetIdUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddQuestionInQuizFromQuestionLibrary implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(AddQuestionInQuizFromQuestionLibrary.class);

    @Autowired
    GetIdUI getIdUI;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuizService quizService;

    @Override
    public void perform() {

        try {
            List<Quiz> quizzes = quizService.getAllQuizzes();
            List<Question> questions = questionService.getAllQuestions();
            LOGGER.info(" =========================QUIZZES =========================");
            quizService.viewQuizTitles(quizzes).forEach(LOGGER::info);
            LOGGER.info(" =========================QUESTIONS =========================");
            questions.forEach(LOGGER::info);
            while (true) {
                if (addQuestionInQuiz(quizService, quizzes, questions)) break;
            }
        } catch (EmptyLibraryException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private boolean addQuestionInQuiz(QuizService quizService, List<Quiz> quizzes, List<Question> questions) {
        try {
            int quizId = getIdUI.getId("Quiz ID");
            Quiz quiz = quizService.findQuiz(quizzes, quizId);
            int questionId = getIdUI.getId("Question ID");
            Question question = questionService.findQuestion(questions, questionId);
            quizService.addQuestionFromQuestionLibrary(quiz, question);
            LOGGER.info("Question Added");
            return true;
        } catch (InvalidIDException e) {
            LOGGER.warn(e.getMessage());
        }
        return false;
    }
}

