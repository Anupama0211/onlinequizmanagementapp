package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;
    private String title;
    private String difficulty;
    private String topic;
    private int marks;
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Option> options;
    //@ManyToMany(mappedBy = "questions" ,cascade=CascadeType.PERSIST)
    //private List<Quiz> quizzes;

    public Question(String title, String difficulty, String topic, int marks) {
        this.title = title;
        this.difficulty = difficulty;
        this.topic = topic;
        this.marks = marks;
    }


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
                    .append(option.getValue())
                    .append("\n");
            optionNumber = (char) (optionNumber + 1);
        }
        return "\n--------------------------------------------------------------------------\n" +
                "ID : " + questionId + "\n" + title + "\n" + optionDisplay +
                "\n--------------------------------------------------------------------------\n";
    }
}

