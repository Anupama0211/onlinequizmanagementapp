package com.epam.userinterface.quizlibraryui.quizoperationsui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class QuizOperationsUIFactory {
    Map<Integer, QuizOperationsUI> quizOperationsUITypes = new HashMap<>();

    @Autowired
    PrintAQuizUI printAQuizUI;
    @Autowired
    CreateAndAddQuizYourselfUI createAndAddQuizYourselfUI;
    @Autowired
    CreateAndAddQuizFromLibraryUI createAndAddQuizFromLibraryUI;
    @Autowired
    RemoveQuizUI removeQuizUI;
    @Autowired
    AddQuestionInQuizOnYourOwnUI addQuestionInQuizOnYourOwnUI;
    @Autowired
    AddQuestionInQuizFromQuestionLibrary addQuestionInQuizFromQuestionLibrary;
    @Autowired
    RemoveQuestionInQuizUI removeQuestionInQuizUI;

//    public QuizOperationsUIFactory() {
//        quizOperationsUITypes.put(1, printAQuizUI);
//        quizOperationsUITypes.put(2, createAndAddQuizYourselfUI);
//        quizOperationsUITypes.put(3, createAndAddQuizFromLibraryUI);
//        quizOperationsUITypes.put(4, removeQuizUI);
//        quizOperationsUITypes.put(5, addQuestionInQuizOnYourOwnUI);
//        quizOperationsUITypes.put(6, addQuestionInQuizFromQuestionLibrary);
//        quizOperationsUITypes.put(7, removeQuestionInQuizUI);
//    }


    public Optional<QuizOperationsUI> getQuizOperations(int choice) {
        quizOperationsUITypes.put(1, printAQuizUI);
        quizOperationsUITypes.put(2, createAndAddQuizYourselfUI);
        quizOperationsUITypes.put(3, createAndAddQuizFromLibraryUI);
        quizOperationsUITypes.put(4, removeQuizUI);
        quizOperationsUITypes.put(5, addQuestionInQuizOnYourOwnUI);
        quizOperationsUITypes.put(6, addQuestionInQuizFromQuestionLibrary);
        quizOperationsUITypes.put(7, removeQuestionInQuizUI);
        return Optional.ofNullable(quizOperationsUITypes.get(choice));
    }
}
