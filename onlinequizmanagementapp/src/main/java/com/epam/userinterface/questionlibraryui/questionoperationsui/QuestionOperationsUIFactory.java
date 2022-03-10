package com.epam.userinterface.questionlibraryui.questionoperationsui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class QuestionOperationsUIFactory {
    Map<Integer, QuestionOperationsUI> questionOperationsUITypes = new HashMap<>();

    @Autowired
    CreateAndAddQuestionUI createAndAddQuestionUI;
    @Autowired
    ModifyQuestionUI modifyQuestionUI;
    @Autowired
    RemoveQuestionsUI removeQuestionsUI;
    @Autowired
    PrintQuestionsUI printQuestionsUI;
//    public  QuestionOperationsUIFactory(){
//        questionOperationsUITypes.put(1,createAndAddQuestionUI);
//        questionOperationsUITypes.put(2,modifyQuestionUI);
//        questionOperationsUITypes.put(3, removeQuestionsUI);
//        questionOperationsUITypes.put(4,printQuestionsUI);
//    }

    public Optional<QuestionOperationsUI> getQuestionOperations(int choice) {
        questionOperationsUITypes.put(1,createAndAddQuestionUI);
        questionOperationsUITypes.put(2,modifyQuestionUI);
        questionOperationsUITypes.put(3, removeQuestionsUI);
        questionOperationsUITypes.put(4,printQuestionsUI);
        return Optional.ofNullable(questionOperationsUITypes.get(choice));
    }
}
