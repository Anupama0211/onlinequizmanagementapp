package com.epam.dto;

import com.epam.entities.Option;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDto {


    int questionId;
    private String title;
    private String difficulty;
    private String topic;
    private int marks;
    private Set<Option> options;


}
