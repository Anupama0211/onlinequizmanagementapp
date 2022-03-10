package com.epam.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizId;
    private String title;
    @ManyToMany(fetch = FetchType.EAGER)
    @Column(unique = true)
    private  Set<Question> questions;

    @Override
    public String toString() {
        StringBuilder quizDisplay = new StringBuilder(title)
                .append("\n--------------------------------------------------------------------------\n");
        int questionNumber = 1;
        for (Question question : questions) {
            quizDisplay.append(questionNumber)
                    .append(question)
                    .append("\n");
            questionNumber++;
        }
        return quizDisplay.toString();
    }
}
