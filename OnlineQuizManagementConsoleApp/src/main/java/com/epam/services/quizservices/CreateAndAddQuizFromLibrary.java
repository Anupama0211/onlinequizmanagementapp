package com.epam.services.quizservices;

import com.epam.database.QuizDatabase;
import com.epam.entities.Quiz;
import com.epam.userinterface.quizlibraryui.QuizGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateAndAddQuizFromLibrary implements QuizLibraryService {
    private static final Logger LOGGER= LogManager.getLogger(CreateAndAddQuizFromLibrary.class);
    @Override
    public void perform() {
        Quiz quiz = QuizGeneratorUI.createAQuizFromQuestionLibrary();
        QuizDatabase.addQuiz(quiz);
        LOGGER.info("Quiz Added!!!");
    }
}
