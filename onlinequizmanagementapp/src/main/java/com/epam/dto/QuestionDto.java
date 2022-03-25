package com.epam.dto;

import com.epam.entities.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class QuestionDto {


    int questionId;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 10, message = "Title should be above 9 characters length")
    private String title;
    @NotEmpty(message = "Difficulty should not be empty")
    private String difficulty;
    @NotEmpty(message = "Topic should not be empty")
    private String topic;

    @Max(value = 5, message = "Marks should not be more than 5")
    @Min(value = 1, message = "Marks should not be less than 1")
    private int marks;
    @NotEmpty(message = "Options should not be empty")
    @Size(min=2, message = "Question should have minimum 2 options")
    private List<Option> options;


}
