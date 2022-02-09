package com.epam.userinterface;

import com.epam.database.QuestionLibrary;
import com.epam.entities.Question;


import java.util.Scanner;

public class QuestionLibraryPortal {
    public static void displayOptions() {
        System.out.println("Welcome to the Questions Library!!!");
        System.out.println("1.Create and add a Question to the library");
        System.out.println("2.Modify a question from the library");
        System.out.println("3.Remove a question from the library");
        System.out.println("4.View Questions in the Library");
        System.out.println("5.Exit Questions Portal");
    }

    public static void createAndAddQuestions() {
        Question question = QuestionGeneratorUI.createAQuestion();
        QuestionLibrary.addQuestion(question);
        System.out.println("Question Added!!!");
    }

    public static void modifyQuestion(Scanner scanner) {
        System.out.println("Following are the questions in the Library");
        QuestionLibrary.printQuestionsInLibrary();
        int questionIndex = getQuestionIndex(scanner);
        Question question = QuestionGeneratorUI.createAQuestion();
        QuestionLibrary.modifyQuestion(question, questionIndex - 1);
    }

    public static void removeQuestions(Scanner scanner) {
        if (QuestionLibrary.size() > 0) {
            System.out.println("Following are the questions in the Library");
            QuestionLibrary.printQuestionsInLibrary();
            int questionIndex = getQuestionIndex(scanner);
            System.out.println("The following question will be removed.....");
            System.out.println(QuestionLibrary.removeQuestion(questionIndex - 1));
        } else {
            System.out.println("Question Library is empty!!!");
        }
    }

    public static int getQuestionIndex(Scanner scanner) {
        int questionIndex = 0;
        do {
            try {
                System.out.println("Enter the question number where you want to do the changes");
                questionIndex = Integer.parseInt(scanner.nextLine());
                if (questionIndex > QuestionLibrary.size()) {
                    System.out.println("Enter the correct question number");
                } else {
                    return questionIndex;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter the correct question number");
            }
        } while (true);
    }

    public static boolean createChangesInQuestionsLibrary(Scanner scanner) {
        do {
            try {
                displayOptions();
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    createAndAddQuestions();
                } else if (choice == 2) {
                    modifyQuestion(scanner);
                } else if (choice == 3) {
                    removeQuestions(scanner);
                } else if (choice == 4) {
                    System.out.println("Following are the questions in the Library");
                    QuestionLibrary.printQuestionsInLibrary();
                } else if (choice == 5) {
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
