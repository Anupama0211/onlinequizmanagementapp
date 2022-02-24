package com.epam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Options`")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionId;
    private String value;
    private boolean isAnswer;
    @ManyToOne
    Question question;

    @Override
    public String toString() {
        return value;
    }
}
