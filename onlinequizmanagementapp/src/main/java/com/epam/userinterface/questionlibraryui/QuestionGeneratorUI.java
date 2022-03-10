package com.epam.userinterface.questionlibraryui;

import com.epam.entities.Option;
import com.epam.entities.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QuestionGeneratorUI {

    private static final Logger LOGGER = LogManager.getLogger(QuestionGeneratorUI.class);

    public Set<Option> createOptions(int noOfOptions, Scanner scanner) {
        LOGGER.info("Enter the  options of the questions");
        Set<Option> options = new HashSet<>();
        for (int i = 0; i < noOfOptions; i++) {
            LOGGER.info("Option {}->", (i + 1));
            Option option = new Option();
            option.setValue(scanner.nextLine());
            LOGGER.info("Is this option the answer for the above question");
            LOGGER.info("Y/N : ");
            while (true) {
                String isAnswer = scanner.nextLine();
                if (isAnswer.equalsIgnoreCase("Y") || isAnswer.equalsIgnoreCase("N")) {
                    option.setAnswer(isAnswer.equalsIgnoreCase("Y"));
                    break;
                } else {
                    LOGGER.info("Enter Y/N only!!!");
                }
            }
            options.add(option);
            LOGGER.info(options);
        }
        LOGGER.info(options);

        return options;
    }

    public Question createAQuestion() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter the Question");
        String title = scanner.nextLine();
        LOGGER.info("Enter the difficulty of the question");
        String difficulty = scanner.nextLine();
        LOGGER.info("Enter the topic to which the question belongs");
        String topic = scanner.nextLine();
        int noOfOptions = 0;
        while (true) {
            try {
                LOGGER.info("Enter the number of options your question has:");
                noOfOptions = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                LOGGER.error("Enter Valid number of Options");
            }
        }
        Set<Option> options = createOptions(noOfOptions, scanner);
        int marks = 0;
        while (true) {
            try {
                LOGGER.info("Enter the marks of the question");
                marks = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                LOGGER.error("Enter Valid number for marks");
            }
        }
        Question question=new Question();
        question.setMarks(marks);
        question.setTitle(title);
        question.setTopic(topic);
        question.setDifficulty(difficulty);
        question.setOptions(options);
        return question;
    }
}