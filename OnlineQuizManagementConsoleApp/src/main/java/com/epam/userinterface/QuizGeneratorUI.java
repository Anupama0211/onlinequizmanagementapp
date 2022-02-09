package com.epam.userinterface;


import com.epam.database.QuestionLibrary;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.QuestionGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizGeneratorUI {
    public static boolean notX(String index) {
        return !(index.equalsIgnoreCase("X"));
    }

    public static Quiz createAQuizFromQuestionLibrary() {
        Scanner scanner = new Scanner(System.in);
        List<Question> questionList = QuestionLibrary.getQuestions();
        int index = 1;
        System.out.println("The following are the questions in the library");
        QuestionLibrary.printQuestionsInLibrary();
        List<Question> questionsToBeAddedInQuiz = new ArrayList<>();
        System.out.println("Enter the title of the quiz");
        String title = scanner.nextLine();
        System.out.println("Chose the questions numbers  you want to add in your Quiz.Press X to stop adding!!");
        String indexOfQuestions = scanner.nextLine();
        while (notX(indexOfQuestions)) {
            try {
                int integerIndex = Integer.parseInt(indexOfQuestions) - 1;
                if (integerIndex <= questionList.size() - 1) {
                    questionsToBeAddedInQuiz.add(questionList.get(integerIndex));
                } else {
                    System.out.println("Enter a valid index");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid numerical index");
            }
            indexOfQuestions = scanner.nextLine();
        }
        return new Quiz(title, questionsToBeAddedInQuiz);
    }

    public static Quiz createQuizQuestionsOwnYourOwn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the quiz");
        String title = scanner.nextLine();
        List<Question> questionsToBeAddedInQuiz = new ArrayList<>();
        System.out.println("Enter the questions to make the quiz.Press x to stop adding");
        System.out.println("Question number -");
        String questionNumber = scanner.nextLine();
        while (notX(questionNumber)) {
            try {
                int questionNumberInteger = Integer.parseInt(questionNumber);
              questionsToBeAddedInQuiz.add(QuestionGeneratorUI.createAQuestion());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid numerical index");
            }
            System.out.println("Question number -");
            questionNumber = scanner.nextLine();
        }
        return new Quiz(title, questionsToBeAddedInQuiz);
    }

}
