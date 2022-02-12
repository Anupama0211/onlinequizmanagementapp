package com.epam.userinterface;


import com.epam.entities.Question;

import java.util.List;
import java.util.Scanner;

public class GetQuestionIndex {
    private GetQuestionIndex() {

    }

    public static int get(List<Question> questions) {
        Scanner scanner = new Scanner(System.in);
        int questionIndex = 0;
        do {
            try {
                System.out.println("Enter the question number.");
                questionIndex = Integer.parseInt(scanner.nextLine());
                if (questionIndex > questions.size() || questionIndex < 0) {
                    System.out.println("Enter the correct question number");
                } else {
                    return questionIndex;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter the correct question number");
            }
        } while (true);
    }

}
