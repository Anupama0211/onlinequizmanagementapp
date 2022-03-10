package com.epam.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Scope("prototype")
@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
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
