package com.epam.services.questionservices;

import com.epam.entities.Option;
import com.epam.entities.Question;

import java.util.List;

public class QuestionBuilder {
    private String title;
    private String difficulty;
    private String topic;
    private List<Option> options;
    private int marks;


    public QuestionBuilder setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public QuestionBuilder setTopic(String topic) {
        this.topic = topic;
        return this;

    }



    public QuestionBuilder setMarks(int marks) {
        this.marks = marks;
        return this;
    }

    public QuestionBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public Question build() {
        return new Question(title, difficulty, topic, marks);
    }
}
