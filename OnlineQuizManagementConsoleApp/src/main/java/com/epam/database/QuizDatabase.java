package com.epam.database;

import com.epam.entities.Question;
import com.epam.entities.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuizDatabase {
    private QuizDatabase() {

    }

    private static List<Quiz> quizzes = new ArrayList<>();

    static {
        quizzes.add(new Quiz());
    }

    public static List<Quiz> get() {
        return quizzes;
    }

    public static Optional<Quiz> getQuiz(int indexOfQuiz) {
        return Optional.ofNullable(quizzes.get(indexOfQuiz));
    }

    public static int size() {
        return quizzes.size();
    }

    public static void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public static void addQuestionInAQuiz(int indexToBeModified, Question question) {
        quizzes.get(indexToBeModified).getQuestionList().add(question);
    }
    public static void deleteQuestionInAQuiz(int indexToBeModified, int questionNumber) {
        quizzes.get(indexToBeModified).getQuestionList().remove(questionNumber);
    }

    public static Quiz deleteQuiz(int indexTobeDeleted) {
        Quiz quiz = quizzes.get(indexTobeDeleted);
        quizzes.remove(indexTobeDeleted);
        return quiz;
    }
}
