package com.epam.dto;

import com.epam.entities.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class QuizDto {

    private int quizId;
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 3, max = 20, message = "Title should be between 3 and 20 characters length")
    private String title;
    private Set<Question> questions;
}
