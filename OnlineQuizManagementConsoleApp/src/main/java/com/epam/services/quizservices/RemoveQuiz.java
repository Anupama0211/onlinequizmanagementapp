package com.epam.services.quizservices;

import com.epam.database.QuizDatabase;
import com.epam.userinterface.GetQuizIndex;

public class RemoveQuiz implements QuizLibraryService {
    @Override
    public void perform() {
        if (QuizDatabase.size() > 0) {
            System.out.println(QuizDatabase.getQuizTitles());
            int quizIndex = GetQuizIndex.get();
            System.out.println("Following Quiz is deleted..");
            System.out.println(QuizDatabase.deleteQuiz(quizIndex - 1));
        } else {
            System.out.println("Quiz Library is empty");
        }
    }
}
