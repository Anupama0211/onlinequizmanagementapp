package com.epam.services.quizservices;


import com.epam.database.QuizDatabase;
import com.epam.entities.Quiz;
import com.epam.userinterface.GetQuestionIndex;
import com.epam.userinterface.GetQuizIndex;

public class RemoveQuestionInQuiz implements QuizLibraryService{
    public void perform() {
        if (QuizDatabase.size() > 0) {
            System.out.println(QuizDatabase.getQuizTitles());
            System.out.println("In which quiz do you want to delete the question");
            int quizIndex = GetQuizIndex.get();
            Quiz quiz = QuizDatabase.getQuiz(quizIndex - 1);
            if (quiz.getQuestionList().isEmpty()) {
                System.out.println(quiz);
                int questionNumber = GetQuestionIndex.get(quiz.getQuestionList());
                QuizDatabase.deleteQuestionInAQuiz(quizIndex, questionNumber - 1);
                System.out.println("Question deleted in the quiz!!!");
            } else {
                System.out.println("Quiz is empty!Add Question in the quiz");
            }
        } else {
            System.out.println("The Quiz library is empty!!!Make a new Quiz");
        }
    }
}
