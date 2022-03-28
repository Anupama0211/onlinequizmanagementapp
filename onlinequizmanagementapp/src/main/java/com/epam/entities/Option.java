package com.epam.entities;


import lombok.*;


import javax.persistence.*;



@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "question_options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private int optionId;
    private String value;
    @Column(name = "is_answer")
    private boolean isAnswer;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


    public Option(int optionId, String value, boolean isAnswer) {
        this.optionId = optionId;
        this.value = value;
        this.isAnswer = isAnswer;
    }

}
