package com.epam.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Scope("prototype")
@NoArgsConstructor
@Getter
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private @Setter
    int questionId;
    private @Setter
    String title;
    private @Setter
    String difficulty;
    private @Setter
    String topic;
    private @Setter
    int marks;
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Option> options;


    public void setOptions(Set<Option> options) {
        options.forEach(option -> option.setQuestion(this));
        this.options = options;
    }



}

