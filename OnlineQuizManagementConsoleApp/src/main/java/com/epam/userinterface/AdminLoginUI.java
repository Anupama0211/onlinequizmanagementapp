package com.epam.userinterface;


import java.util.Scanner;

public class AdminLoginUI {

    public static void displayOptions() {
        System.out.println("Welcome to the Admin Portal!!!");
        System.out.println("1.Questions Library");
        System.out.println("2.Quiz Library");
        System.out.println("3.Exit from the Admin portal");
        System.out.println("Enter your choice");

    }

    public static boolean goToTheLibraries(Scanner scanner) {
        do {
            try {
                displayOptions();
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                   QuestionLibraryPortal.createChangesInQuestionsLibrary(scanner);
                } else if (choice == 2) {
                    QuizLibraryPortal.createChangesInTheQuizLibrary(scanner);
                } else if (choice == 3) {
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