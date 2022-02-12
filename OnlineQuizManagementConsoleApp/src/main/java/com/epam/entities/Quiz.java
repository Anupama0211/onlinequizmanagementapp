package com.epam.entities;

import com.epam.database.QuestionsDatabase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Quiz {
    private String title;
    private List<Question> questionList;

    public Quiz() {
        title = "Default Java Quiz";
        questionList = QuestionsDatabase.getQuestions();
    }

    @Override
    public String toString() {
        String quizDisplay = title+"\n--------------------------------------------------------\n";
        int questionNumber=1;
        for (Question question : questionList) {
            quizDisplay += questionNumber+" "+question + "\n";
            questionNumber++;
        }
        return quizDisplay;
    }
}
