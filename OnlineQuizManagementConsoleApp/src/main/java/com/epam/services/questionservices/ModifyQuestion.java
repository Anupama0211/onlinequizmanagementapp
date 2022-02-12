package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;
import com.epam.userinterface.GetQuestionIndex;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;

public class ModifyQuestion implements QuestionLibraryService {
    public void perform() {
        if (QuestionsDatabase.size() > 0) {
            System.out.println("Following are the questions in the Library");
            new PrintQuestions().perform();
            int questionIndex = GetQuestionIndex.get(QuestionsDatabase.getQuestions());
            Question question = QuestionGeneratorUI.createAQuestion();
            QuestionsDatabase.modifyQuestion(question, questionIndex - 1);
            System.out.println("Question Modified!");
        } else {
            System.out.println("The question library is empty");
        }
    }
}
