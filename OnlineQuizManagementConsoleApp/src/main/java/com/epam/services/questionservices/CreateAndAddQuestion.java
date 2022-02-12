package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;

public class CreateAndAddQuestion implements QuestionLibraryService {
    @Override
    public void perform() {
        Question question = QuestionGeneratorUI.createAQuestion();
        QuestionsDatabase.addQuestion(question);
        System.out.println("Question Added!!!");
    }
}
