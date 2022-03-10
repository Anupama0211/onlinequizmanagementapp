package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuizService;
import com.epam.userinterface.GetIdUI;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AddQuestionInQuizOnYourOwnUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(AddQuestionInQuizOnYourOwnUI.class);

    @Autowired
    GetIdUI getIdUI;
    @Autowired
    QuestionGeneratorUI questionGeneratorUI;
    @Autowired
    QuizService quizService;
    @Override
    public void perform() {
        try {
            List<Quiz> quizzes = quizService.getAllQuizzes();
            quizService.viewQuizTitles(quizzes).forEach(LOGGER::info);
            while (true) {
                if (addQuestion(quizService, quizzes)) break;
            }
        } catch (EmptyLibraryException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private boolean addQuestion(QuizService quizService, List<Quiz> quizzes) {
        try {
            int quizId = getIdUI.getId("Quiz ID");
            Question question = questionGeneratorUI.createAQuestion();
            Quiz quiz = quizService.findQuiz(quizzes, quizId);
            quizService.addQuetsionInQuizOnYourOwn(quiz, question);
            LOGGER.info("Question added in the quiz");
            return true;
        } catch (InvalidIDException e) {
            LOGGER.warn(e.getMessage());
        }
        return false;
    }
}

