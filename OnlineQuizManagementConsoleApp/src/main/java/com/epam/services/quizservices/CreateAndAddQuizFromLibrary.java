package com.epam.services.quizservices;

import com.epam.database.QuizDatabase;
import com.epam.entities.Quiz;
import com.epam.userinterface.quizlibraryui.QuizGeneratorUI;

public class CreateAndAddQuizFromLibrary implements QuizLibraryService {
    @Override
    public void perform() {
        Quiz quiz = QuizGeneratorUI.createAQuizFromQuestionLibrary();
        QuizDatabase.addQuiz(quiz);
        System.out.println("Quiz Added!!!");
    }
}
