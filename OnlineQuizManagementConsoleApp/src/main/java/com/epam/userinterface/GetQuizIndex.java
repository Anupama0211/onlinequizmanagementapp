package com.epam.userinterface;

import com.epam.database.QuizDatabase;

import java.util.Scanner;

public class GetQuizIndex {
    private GetQuizIndex() {
    }

    public static int get() {
        Scanner scanner = new Scanner(System.in);
        int quizIndex = 0;
        do {
            try {
                System.out.println("Enter the quiz number");
                quizIndex = Integer.parseInt(scanner.nextLine());
                if (quizIndex > QuizDatabase.size() || quizIndex < 0) {
                    System.out.println("Enter the correct quiz number");
                } else {
                    return quizIndex;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter the correct quiz number");
            }
        } while (true);
    }
}
