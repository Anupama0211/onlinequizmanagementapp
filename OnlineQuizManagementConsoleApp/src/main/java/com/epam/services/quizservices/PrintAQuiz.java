package com.epam.services.quizservices;

import com.epam.database.QuestionsDatabase;
import com.epam.database.QuizDatabase;
import com.epam.userinterface.GetQuizIndex;


public class PrintAQuiz implements QuizLibraryService {
    @Override
    public void perform() {
        if (QuestionsDatabase.size() > 0) {
            System.out.println(QuizDatabase.getQuizTitles());
            int quizIndex = GetQuizIndex.get();
            System.out.println(QuizDatabase.getQuiz(quizIndex - 1));
        } else {
            System.out.println("Quiz Library is Empty");
        }
    }
}