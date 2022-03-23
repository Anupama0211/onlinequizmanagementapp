package com.epam.dto;

import com.epam.entities.Option;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class QuestionDto {


    int questionId;
    private String title;
    private String difficulty;
    private String topic;
    private int marks;
    private List<Option> options;


}
