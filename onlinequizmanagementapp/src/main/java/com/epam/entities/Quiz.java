package com.epam.entities;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private int quizId;
    private String title;
    @ManyToMany(fetch = FetchType.EAGER)
    @Column(unique = true)
    private Set<Question> questions;


}
