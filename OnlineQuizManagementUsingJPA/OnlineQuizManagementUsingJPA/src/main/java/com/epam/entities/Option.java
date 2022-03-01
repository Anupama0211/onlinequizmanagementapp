package com.epam.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Question_Options")
public class Option {

    public Option(int optionId, String value, boolean isAnswer) {
        this.optionId = optionId;
        this.value = value;
        this.isAnswer = isAnswer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionId;
    private String value;
    private boolean isAnswer;
    @ManyToOne
    private Question question;

    @Override
    public String toString() {
        return value;
    }
}
