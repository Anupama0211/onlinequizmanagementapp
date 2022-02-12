package com.epam.userinterface.quizlibraryui;


import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.questionservices.PrintQuestions;
import com.epam.userinterface.GetQuestionIndex;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizGeneratorUI {
    private QuizGeneratorUI() {

    }

    public static Quiz createAQuizFromQuestionLibrary() {
        Scanner scanner = new Scanner(System.in);
        List<Question> questionList = QuestionsDatabase.getQuestions();
        System.out.println("The following are the questions in the library");
        new PrintQuestions().perform();
        List<Question> questionsToBeAddedInQuiz = new ArrayList<>();
        System.out.println("Enter the title of the quiz");
        String title = scanner.nextLine();
        int noOfQuestions;
        int indexOfQuestion;
        do {
            try {
                System.out.println("Enter how many questions you want to add");
                noOfQuestions = Integer.parseInt(scanner.nextLine());
                if (noOfQuestions <= QuestionsDatabase.size()) {
                    System.out.println("Chose the questions number  you want to add in your Quiz.");
                    for (int i = 0; i < noOfQuestions; i++) {
                        indexOfQuestion = GetQuestionIndex.get(questionList);
                        questionsToBeAddedInQuiz.add(questionList.get(indexOfQuestion));
                    }
                } else {
                    System.out.println("We have only " + QuestionsDatabase.size() + " questions in the library");
                    noOfQuestions = -1;
                    System.out.println("Enter a valid number");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number");
                noOfQuestions = -1;
            }
        } while (noOfQuestions < 0);
        System.out.println("Quiz Made!");

        return new Quiz(title, questionsToBeAddedInQuiz);
    }

    public static Quiz createQuizQuestionsOwnYourOwn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the quiz");
        String title = scanner.nextLine();
        List<Question> questionsToBeAddedInQuiz = new ArrayList<>();
        int noOfQuestions;
        do {
            try {
                System.out.println("Enter how many questions you want to add");
                noOfQuestions = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < noOfQuestions; i++) {
                    questionsToBeAddedInQuiz.add(QuestionGeneratorUI.createAQuestion());
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number");
                noOfQuestions = -1;
            }
        } while (noOfQuestions < 0);

        System.out.println("Quiz Made!");
        return new Quiz(title, questionsToBeAddedInQuiz);
    }

}
