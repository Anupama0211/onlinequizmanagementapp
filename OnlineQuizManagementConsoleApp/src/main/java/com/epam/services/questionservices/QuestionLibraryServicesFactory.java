package com.epam.services.questionservices;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QuestionLibraryServicesFactory {
    Map<Integer, QuestionLibraryService> questionServicesTypes = new HashMap<>();

    public QuestionLibraryServicesFactory() {
        questionServicesTypes.put(1, new CreateAndAddQuestion());
        questionServicesTypes.put(2, new ModifyQuestion());
        questionServicesTypes.put(3, new RemoveQuestions());
        questionServicesTypes.put(4, new PrintQuestions());
    }

    public Optional<QuestionLibraryService> getQuestionServices(int choice) {

       return Optional.ofNullable(questionServicesTypes.get(choice));
    }


}

