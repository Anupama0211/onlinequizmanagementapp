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

import java.util.List;
import java.util.Optional;

@Component
public class AddQuestionInQuizFromQuestionLibrary implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(AddQuestionInQuizFromQuestionLibrary.class);

    @Autowired
    GetIdUI getIdUI;
    @Autowired
    QuestionService questionService;

    @Override
    public void perform(QuizService quizService) {

        try {
            List<Quiz> quizzes = quizService.getAllQuizzes();
            List<Question> questions = questionService.getAllQuestions();
            LOGGER.info(" =========================QUIZZES =========================");
            quizService.viewQuizTitles(quizzes).forEach(LOGGER::info);
            int quizId = getIdUI.getId("Quiz ID");
            Quiz quiz = quizService.findQuiz(quizzes, quizId);
            LOGGER.info(" =========================QUESTIONS =========================");
            questions.forEach(LOGGER::info);
            while (true) {
                try {
                    int questionId = getIdUI.getId("Question ID");
                    Question question = questionService.findQuestion(questions, questionId);
                    quizService.addQuestionFromQuestionLibrary(quiz, question);
                    LOGGER.info("Question Added");
                    break;
                } catch (InvalidIDException e) {
                    LOGGER.warn(e.getMessage());
                }
            }
        } catch (EmptyLibraryException | InvalidIDException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}

