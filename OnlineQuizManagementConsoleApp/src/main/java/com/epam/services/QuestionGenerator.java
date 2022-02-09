package com.epam.services;

import com.epam.entities.Question;

public class QuestionGenerator {

    public static Question makeQuestions(String title, String difficulty, String topic, String[] options, String answer, int marks) {
        return new Question(title, difficulty, topic, options, answer, marks);
    }
}

