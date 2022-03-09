package com.epam.userinterface.quizlibraryui.quizoperationsui;


import com.epam.GetApplicationContext;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QuizOperationsUIFactory {
    Map<Integer, QuizOperationsUI> quizOperationsUITypes = new HashMap<>();

    public QuizOperationsUIFactory() {
        ApplicationContext context = GetApplicationContext.get();
        quizOperationsUITypes.put(1, context.getBean(PrintAQuizUI.class));
        quizOperationsUITypes.put(2, new CreateAndAddQuizYourselfUI());
        quizOperationsUITypes.put(3, context.getBean(CreateAndAddQuizFromLibraryUI.class));
        quizOperationsUITypes.put(4, context.getBean(RemoveQuizUI.class));
        quizOperationsUITypes.put(5, context.getBean(AddQuestionInQuizOnYourOwnUI.class));
        quizOperationsUITypes.put(6, context.getBean(AddQuestionInQuizFromQuestionLibrary.class));
        quizOperationsUITypes.put(7, context.getBean(RemoveQuestionInQuizUI.class));
    }


    public Optional<QuizOperationsUI> getQuizOperations(int choice) {
        return Optional.ofNullable(quizOperationsUITypes.get(choice));
    }
}
