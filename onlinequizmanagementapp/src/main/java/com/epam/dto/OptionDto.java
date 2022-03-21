package com.epam.dto;

import com.epam.entities.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@NoArgsConstructor
public class OptionDto {
    private int optionId;
    private String value;
    private boolean isAnswer;
    private Question question;

    public OptionDto(int optionId, String value, boolean isAnswer) {
        this.optionId = optionId;
        this.value = value;
        this.isAnswer = isAnswer;
    }
}
