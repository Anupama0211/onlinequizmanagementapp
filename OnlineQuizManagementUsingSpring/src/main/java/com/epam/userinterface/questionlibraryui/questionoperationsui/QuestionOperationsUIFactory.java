package com.epam.userinterface.questionlibraryui.questionoperationsui;

import com.epam.GetApplicationContext;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QuestionOperationsUIFactory {
    Map<Integer, QuestionOperationsUI> questionOperationsUITypes = new HashMap<>();

    public QuestionOperationsUIFactory() {
        ApplicationContext context= GetApplicationContext.get();
        questionOperationsUITypes.put(1, new CreateAndAddQuestionUI());
        questionOperationsUITypes.put(2,context.getBean(ModifyQuestionUI.class));
        questionOperationsUITypes.put(3, context.getBean(RemoveQuestionsUI.class));
        questionOperationsUITypes.put(4, new PrintQuestionsUI());
    }

    public Optional<QuestionOperationsUI> getQuestionOperations(int choice) {

        return Optional.ofNullable(questionOperationsUITypes.get(choice));
    }
}
