package com.epam.userinterface.quizlibraryui;


import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.questionservices.PrintQuestions;
import com.epam.userinterface.GetQuestionIndex;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizGeneratorUI {
    private static final Logger LOGGER = LogManager.getLogger(QuizGeneratorUI.class);

    static String display = "Enter a valid number";

    private QuizGeneratorUI() {

    }

    public static Quiz createAQuizFromQuestionLibrary() {
        Scanner scanner = new Scanner(System.in);
        List<Question> questionList = QuestionsDatabase.getQuestions();
        LOGGER.info("The following are the questions in the library");
        new PrintQuestions().perform();
        List<Question> questionsToBeAddedInQuiz = new ArrayList<>();
        LOGGER.info("Enter the title of the quiz");
        String title = scanner.nextLine();
        int noOfQuestions;
        int indexOfQuestion;
        do {
            try {
                LOGGER.info("Enter how many questions you want to add");
                noOfQuestions = Integer.parseInt(scanner.nextLine());
                if (noOfQuestions <= QuestionsDatabase.size()) {
                    LOGGER.info("Chose the questions number  you want to add in your Quiz.");
                    for (int i = 0; i < noOfQuestions; i++) {
                        indexOfQuestion = GetQuestionIndex.get(questionList);
                        questionsToBeAddedInQuiz.add(questionList.get(indexOfQuestion));
                    }
                } else {
                    LOGGER.info("We have only " + QuestionsDatabase.size() + "  questions in the library ");
                    noOfQuestions = -1;
                    LOGGER.info(display);
                }
            } catch (NumberFormatException e) {
                LOGGER.info(display);
                noOfQuestions = -1;
            }
        } while (noOfQuestions < 0);
        LOGGER.info("Quiz Made!");

        return new Quiz(title, questionsToBeAddedInQuiz);
    }

    public static Quiz createQuizQuestionsOwnYourOwn() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter the title of the quiz");
        String title = scanner.nextLine();
        List<Question> questionsToBeAddedInQuiz = new ArrayList<>();
        int noOfQuestions;
        do {
            try {
                LOGGER.info("Enter how many questions you want to add");
                noOfQuestions = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < noOfQuestions; i++) {
                    Question question = QuestionGeneratorUI.createAQuestion();
                    questionsToBeAddedInQuiz.add(question);
                    QuestionsDatabase.addQuestion(question);
                }
            } catch (NumberFormatException e) {
                LOGGER.info(display);
                noOfQuestions = -1;
            }
        } while (noOfQuestions < 0);

        LOGGER.info("Quiz Made!");
        return new Quiz(title, questionsToBeAddedInQuiz);
    }

}
