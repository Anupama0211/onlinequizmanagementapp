package com.epam.dto;

import com.epam.entities.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class QuizDto {

    private int quizId;
    private String title;
    private Set<Question> questions;



}
