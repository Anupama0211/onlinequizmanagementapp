package com.epam.userinterface.quizlibraryui;


import com.epam.services.quizservices.QuizLibraryService;
import com.epam.services.quizservices.QuizLibraryServicesFactory;


import java.util.Optional;
import java.util.Scanner;

public class QuizLibraryPortal {
    private QuizLibraryPortal() {

    }

    public static void displayOptions() {
        System.out.println("Welcome to the Quiz Library!!!");
        System.out.println("1.View a Quiz");
        System.out.println("2.Create and Add a Quiz On Your Own");
        System.out.println("3.Create and Add a Quiz From Question Library");
        System.out.println("4.Delete a Quiz");
        System.out.println("5.Add a question in a Quiz");
        System.out.println("6.Delete a question in a Quiz");
        System.out.println("7.Exit Quiz Portal");
        System.out.println("Enter your choice");
    }

    public static boolean modifyTheQuizLibrary(Scanner scanner) {
        QuizLibraryServicesFactory quizLibraryServicesFactory = new QuizLibraryServicesFactory();
        do {
            try {
                displayOptions();
                int choice = Integer.parseInt(scanner.nextLine());
                Optional<QuizLibraryService> quizLibraryService = quizLibraryServicesFactory.getQuizServices(choice);
                if (quizLibraryService.isPresent()) {
                    quizLibraryService.get().perform();
                } else if (choice == 7) {
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

