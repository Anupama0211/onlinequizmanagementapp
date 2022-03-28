package com.epam.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class OptionDto {
    private int optionId;
    private String value;
    private boolean isAnswer;



    public OptionDto(int optionId, String value, boolean isAnswer) {
        this.optionId = optionId;
        this.value = value;
        this.isAnswer = isAnswer;
    }
}
