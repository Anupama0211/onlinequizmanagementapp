package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;

import java.util.List;

public class PrintQuestions implements QuestionLibraryService {
    public void perform() {
        if (QuestionsDatabase.size() > 0) {
            List<Question> questions = QuestionsDatabase.getQuestions();
            int index = 1;
            for (Question question : questions) {
                System.out.println(index + " " + question);
                index++;
            }
        } else {
            System.out.println("The question library is empty!!!");
        }
    }
}
