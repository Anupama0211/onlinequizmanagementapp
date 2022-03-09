package com.epam.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@Getter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Setter int questionId;
    private @Setter String title;
    private @Setter String difficulty;
    private @Setter String topic;
    private @Setter int marks;
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Option> options;


    public void setOptions(List<Option> options) {
        options.forEach(option -> option.setQuestion(this));
        this.options = options;
    }

    @Override
    public String toString() {
        StringBuilder optionDisplay = new StringBuilder();
        char optionNumber = 'a';
        for (Option option : options) {
            optionDisplay.append(optionNumber)
                    .append(".")
                    .append(option)
                    .append("\n");
            optionNumber = (char) (optionNumber + 1);
        }
        return "\n--------------------------------------------------------------------------\n" +
                "ID : " + questionId + "\n" + title + "\n" + optionDisplay +
                "\n--------------------------------------------------------------------------\n";
    }
}

