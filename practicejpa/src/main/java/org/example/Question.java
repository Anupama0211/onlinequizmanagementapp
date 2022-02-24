package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Option;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private @Setter String title;
    private @Setter String difficulty;
    private @Setter String topic;
    private @Setter int marks;
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    List<Option> options;

    public Question() {

    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Question(String difficulty, String title) {
        this.difficulty = difficulty;
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public int getMarks() {
        return marks;
    }

    public int getId() {
        return id;
    }

    public void setQuestionId(int id) {
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Question [optionId=" + id + ", title=" + title + ", difficulty=" + difficulty + "]";
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        options.forEach(option -> {
            option.setQuestion(this);
        });
        this.options = options;
    }
}

