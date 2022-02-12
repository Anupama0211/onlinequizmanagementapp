package com.epam.services.quizservices;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QuizLibraryServicesFactory {
    Map<Integer, QuizLibraryService> quizServicesTypes=new HashMap<>();
    public QuizLibraryServicesFactory(){
        quizServicesTypes.put(1,new PrintAQuiz());
        quizServicesTypes.put(2,new CreateAndAddQuizYourself());
        quizServicesTypes.put(3,new CreateAndAddQuizFromLibrary());
        quizServicesTypes.put(4,new RemoveQuiz());
        quizServicesTypes.put(5,new AddQuestionInQuiz());
        quizServicesTypes.put(6,new RemoveQuestionInQuiz());
    }


    public Optional<QuizLibraryService> getQuizServices(int choice){
        return Optional.ofNullable(quizServicesTypes.get(choice));
    }
}
