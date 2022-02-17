package com.epam.services.quizservices;

import com.epam.database.QuizDatabase;
import com.epam.entities.Quiz;

import java.util.List;
import java.util.stream.Collectors;

public class QuizTitles {
    public String get() {
        List<String> quizTiltles = QuizDatabase
                .get()
                .stream()
                .map(Quiz::getTitle)
                .collect(Collectors.toList());
        String titles = "";
        int index = 0;
        for (String title : quizTiltles) {
            index++;
            titles += (index + ". " + title + "\n");
        }
        return titles;
    }
}
