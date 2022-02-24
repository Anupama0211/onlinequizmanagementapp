package com.epam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int quizId;
    private String title;
    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    //private List<Question> questions;




    @Override
    public String toString() {
        StringBuilder quizDisplay = new StringBuilder(title)
                .append("\n--------------------------------------------------------------------------\n");
        int questionNumber = 1;
//        for (Question question : questions) {
//            quizDisplay.append(questionNumber)
//                    .append(question)
//                    .append("\n");
//            questionNumber++;
//        }
        return quizDisplay.toString();
    }
}
