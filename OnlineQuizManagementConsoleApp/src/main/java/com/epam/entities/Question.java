package com.epam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Question {
    private String title;
    private String difficulty;
    private String topic;
    private String[] options;
    private String answer;
    private int marks;

    @Override
    public String toString() {
        String optionDisplay = "";
        for (String option : options) {
            optionDisplay += option + "\n";
        }
        return title + "\n" + optionDisplay + "\n--------------------------------------------------------\n";
    }
}

