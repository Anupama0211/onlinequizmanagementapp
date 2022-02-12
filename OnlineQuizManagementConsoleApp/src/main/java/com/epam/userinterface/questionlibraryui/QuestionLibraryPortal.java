package com.epam.userinterface.questionlibraryui;

import com.epam.services.questionservices.QuestionLibraryService;
import com.epam.services.questionservices.QuestionLibraryServicesFactory;


import java.util.Optional;
import java.util.Scanner;

public class QuestionLibraryPortal {
    private QuestionLibraryPortal() {

    }

    public static void displayOptions() {
        System.out.println("Welcome to the Questions Library!!!");
        System.out.println("1.Create and add a Question to the library");
        System.out.println("2.Modify a question from the library");
        System.out.println("3.Remove a question from the library");
        System.out.println("4.View Questions in the Library");
        System.out.println("5.Exit Questions Portal");
        System.out.println("Enter your choice--");
    }

    public static boolean modifyInQuestionsLibrary(Scanner scanner) {
        QuestionLibraryServicesFactory questionLibraryServiceFactory = new QuestionLibraryServicesFactory();
        do {
            try {
                displayOptions();
                int choice = Integer.parseInt(scanner.nextLine());
                Optional<QuestionLibraryService> questionLibraryService = questionLibraryServiceFactory.getQuestionServices(choice);
                if (questionLibraryService.isPresent()) {
                    questionLibraryService.get().perform();
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

