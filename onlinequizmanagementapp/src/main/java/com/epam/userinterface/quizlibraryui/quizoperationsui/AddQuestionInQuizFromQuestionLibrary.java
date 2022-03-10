package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
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

        Optional<List<Quiz>> quizzesOptional = quizService.getAllQuizzes();
        Optional<List<Question>> questionsOptional = questionService.getAllQuestions();
        if (!quizzesOptional.isPresent()) {
            LOGGER.info("Quiz Library is empty!!");
        } else if (!questionsOptional.isPresent()) {
            LOGGER.info("Questions Library is empty!!");
        } else {
            while (true) {
                LOGGER.info(" =========================QUIZZES =========================");
                quizService.viewQuizTitles(quizzesOptional.get()).forEach(LOGGER::info);
                int quizId = getIdUI.getId("Quiz ID");
                Optional<Quiz> quizOptional = quizService.findQuiz(quizzesOptional.get(), quizId);
                LOGGER.info(" =========================QUESTIONS =========================");
                questionsOptional.get().forEach(LOGGER::info);
                int questionId = getIdUI.getId("Question ID");
                Optional<Question> questionOptional = questionService.findQuestion(questionsOptional.get(), questionId);
                if (quizService.addQuestionFromQuestionLibrary(quizOptional, questionOptional)) {
                    LOGGER.info("Question Added");
                    break;
                } else {
                    LOGGER.info("Wrong Question ID or Quiz ID!!");
                }
            }
        }
    }
}
