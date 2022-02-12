package com.epam.services.quizservices;


import com.epam.database.QuizDatabase;
import com.epam.entities.Question;
import com.epam.userinterface.GetQuizIndex;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;

public class AddQuestionInQuiz implements QuizLibraryService {
    @Override
    public void perform() {
        if (QuizDatabase.size() > 0) {
            System.out.println(QuizDatabase.getQuizTitles());
            System.out.println("In which quiz do you want to add the question");
            int quizIndex = GetQuizIndex.get();
            Question question = QuestionGeneratorUI.createAQuestion();
            QuizDatabase.addQuestionInAQuiz(quizIndex - 1, question);
            System.out.println("Question added in the Quiz!!!");
        } else {
            System.out.println("The Quiz library is empty!!!Make a new Quiz");
        }
    }
}
