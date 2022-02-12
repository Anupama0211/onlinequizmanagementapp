package com.epam.userinterface;

import java.util.Scanner;

public class QuizPortalUI {
    private QuizPortalUI() {

    }
    public static void displayOptions() {
        System.out.println("-----------------WELCOME TO THE QUIZ PORTAL-----------------");
        System.out.println("1.Login as Admin");
        System.out.println("2.Login as Player");
        System.out.println("3.Exit");
    }


    public static void launch(Scanner scanner) {

        int choice = 0;
        boolean loginStatus = false;
        while (choice != 3) {
            try {
                displayOptions();
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    loginStatus = LoginUI.loginUser(scanner);
                    if (loginStatus) {
                        AdminLoginUI.goToTheLibraries(scanner);
                    }
                } else if (choice == 2) { //PlayerLogin
                } else if (choice == 3) {
                    System.out.println("Exited......");
                    break;
                } else {
                    System.out.println("Enter a valid choice!!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid choice!!");
            }
        }
    }
}



