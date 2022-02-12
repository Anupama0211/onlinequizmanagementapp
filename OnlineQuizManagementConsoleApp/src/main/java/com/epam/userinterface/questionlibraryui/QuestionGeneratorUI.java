package com.epam.userinterface.questionlibraryui;

import com.epam.entities.Question;
import com.epam.services.questionservices.QuestionBuilder;

import java.util.Scanner;

public class QuestionGeneratorUI {
    private QuestionGeneratorUI() {

    }

    public static Question createAQuestion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Question");
        String title = scanner.nextLine();
        System.out.println("Enter the difficulty of the question");
        String difficulty = scanner.nextLine();
        System.out.println("Enter the topic to which the question belongs");
        String topic = scanner.nextLine();
        System.out.println("Enter the  options of the questions");
        String[] options = new String[4];
        char c = 'a';
        for (int i = 0; i < 4; i++) {
            System.out.printf("Option %d->", (i + 1));
            options[i] = (char) (c + 1) + scanner.nextLine();
        }
        System.out.println("Enter the answer of the question");
        String answer = scanner.nextLine();
        System.out.println("Enter the marks of the question");
        int marks = scanner.nextInt();

        return new QuestionBuilder().setAnswer(answer)
                .setDifficulty(difficulty)
                .setMarks(marks)
                .setOptions(options)
                .setTopic(topic)
                .setTitle(title)
                .build();
    }
}
