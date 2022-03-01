package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.QuizService;
import com.epam.userinterface.GetIdUI;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class AddQuestionInQuizOnYourOwnUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(AddQuestionInQuizOnYourOwnUI.class);

    @Override
    public void perform(QuizService quizService) {
        Optional<List<Quiz>> optionalQuizzes = quizService.getAllQuizzes();

        if (optionalQuizzes.isPresent()) {
            List<Quiz> quizzes=optionalQuizzes.get();
            quizService.viewQuizTitles(quizzes).forEach(LOGGER::info);
            while (true) {
                if (addQuestion(quizService, quizzes)) {
                    break;
                }
            }
        } else {
            LOGGER.info("Quiz Library is empty!!!");
        }
    }

    private boolean addQuestion(QuizService quizService, List<Quiz> quizzes) {
        GetIdUI getIdUI = new GetIdUI();
        int quizId;
        Question question = new QuestionGeneratorUI().createAQuestion();
        quizId = getIdUI.getId("Quiz ID");
        Optional<Quiz> quizOptional = quizService.findQuiz(quizzes, quizId);
        if (quizService.addQuetsionInQuizOnYourOwn(quizOptional, question)) {
            LOGGER.info("Question added in the quiz");
            return true;
        } else {
            LOGGER.info("Wrong quizID");
        }
        return false;
    }
}
