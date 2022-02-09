package com.epam.userinterface;


import com.epam.database.QuizLibrary;
import com.epam.entities.Question;
import com.epam.entities.Quiz;

import java.util.Scanner;

public class QuizLibraryPortal {
    public static void displayOptions() {
        System.out.println("Welcome to the Quiz Library!!!");
        System.out.println("1.View a Quiz");
        System.out.println("2.View Quiz titles");
        System.out.println("3.Create and Add a Quiz On Your Own");
        System.out.println("4.Create and Add a Quiz From Question Library");
        System.out.println("5.Delete a Quiz");
        System.out.println("6.Add a question in a Quiz");
        System.out.println("7.Delete a question in a Quiz");
        System.out.println("8.Exit Quiz Portal");
    }

    public static void deleteAQuestionInTheQuiz(Scanner scanner) {
        if (QuizLibrary.size() > 0) {
            viewQuizTitles();
            System.out.println("Which quiz do you want to delete the question");
            int quizIndex = getQuizIndex(scanner);
            Quiz quiz = QuizLibrary.getQuiz(quizIndex - 1);
            System.out.println(quiz);
            System.out.println("Which question do you want to delete");
            int questionNumber = scanner.nextInt();
            QuizLibrary.deleteQuestionInAQuiz(quizIndex, questionNumber-1);
            System.out.println("Question deleted in the quiz!!!");
        } else {
            System.out.println("The Quiz library is empty!!!Make a new Quiz");
        }
    }

    public static void addAQuestionInTheQuiz(Scanner scanner) {
        if (QuizLibrary.size() > 0) {
            viewQuizTitles();
            System.out.println("Which quiz do you want to add the question");
            int quizIndex = getQuizIndex(scanner);
            Question question = QuestionGeneratorUI.createAQuestion();
            QuizLibrary.addQuestionInAQuiz(quizIndex - 1, question);
            System.out.println("Question added in the Quiz!!!");
        } else {
            System.out.println("The Quiz library is empty!!!Make a new Quiz");
        }
    }

    public static int getQuizIndex(Scanner scanner) {
        int quizIndex = 0;
        do {
            try {
                System.out.println("Enter the quiz number");
                quizIndex = Integer.parseInt(scanner.nextLine());
                if (quizIndex > QuizLibrary.size()) {
                    System.out.println("Enter the correct quiz number");
                } else {
                    return quizIndex;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter the correct quiz number");
            }
        } while (true);
    }

    public static void viewQuizTitles() {
        System.out.println("Following are the quiz titles");
        QuizLibrary.printQuizTitles();
    }

    public static void viewAQuiz(Scanner scanner) {
        viewQuizTitles();
        int quizIndex = getQuizIndex(scanner);
        System.out.println(QuizLibrary.getQuiz(quizIndex - 1));
    }

    public static void deleteAQuiz(Scanner scanner) {
        if (QuizLibrary.size() > 0) {
            viewQuizTitles();
            int quizIndex = getQuizIndex(scanner);
            System.out.println("Following quiz will be removed");
            System.out.println(QuizLibrary.deleteQuiz(quizIndex - 1).getTitle());
        } else {
            System.out.println("Quiz Library is empty");
        }
    }


    public static boolean createChangesInTheQuizLibrary(Scanner scanner) {
        do {
            try {
                displayOptions();
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    viewAQuiz(scanner);
                } else if (choice == 2) {
                    viewQuizTitles();
                } else if (choice == 3) {
                    Quiz quiz = QuizGeneratorUI.createQuizQuestionsOwnYourOwn();
                    QuizLibrary.addQuiz(quiz);
                    System.out.println("Quiz Added!!!");
                } else if (choice == 4) {
                    Quiz quiz = QuizGeneratorUI.createAQuizFromQuestionLibrary();
                    QuizLibrary.addQuiz(quiz);
                    System.out.println("Quiz Added!!!");
                } else if (choice == 5) {
                    deleteAQuiz(scanner);
                } else if (choice == 6) {
                    addAQuestionInTheQuiz(scanner);
                } else if (choice == 7) {
                    deleteAQuestionInTheQuiz(scanner);
                } else if (choice == 8) {
                    return false;
                } else {
                    System.out.println("Enter a valid choice!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid choice!!!");
            }
        } while (true);
    }

}

