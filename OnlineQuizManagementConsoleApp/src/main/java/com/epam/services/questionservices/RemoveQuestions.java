package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;
import com.epam.userinterface.GetQuestionIndex;

public class RemoveQuestions implements QuestionLibraryService {

    public void perform() {
        if (QuestionsDatabase.size() > 0) {
            System.out.println("Following are the questions in the Library");
            new PrintQuestions().perform();
            int questionIndex = GetQuestionIndex.get(QuestionsDatabase.getQuestions());
            System.out.println("The following question is removed.....");
            System.out.println(QuestionsDatabase.removeQuestion(questionIndex - 1));
        } else {
            System.out.println("Question Library is empty!!!");
        }
    }
}
