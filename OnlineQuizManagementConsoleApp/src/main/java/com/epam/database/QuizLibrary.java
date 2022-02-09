package com.epam.database;

import com.epam.entities.Question;
import com.epam.entities.Quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuizLibrary {
    private static List<Quiz> quizzes = new ArrayList<>(Arrays.asList(new Quiz()));


    public static Quiz getQuiz(int indexOfQuiz) {
        return quizzes.get(indexOfQuiz);
    }

    public static int size() {
        return quizzes.size();
    }

    public static void printQuizTitles() {
        List<String> quizTiltles = quizzes.stream()
                .map(quiz -> quiz.getTitle())
                .collect(Collectors.toList());
        int index = 0;
        for (String title : quizTiltles) {
            index++;
            System.out.println(index + ". " + title);
        }
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
        Quiz quiz=quizzes.get(indexTobeDeleted);
        quizzes.remove(indexTobeDeleted);
        return quiz;
    }
}
