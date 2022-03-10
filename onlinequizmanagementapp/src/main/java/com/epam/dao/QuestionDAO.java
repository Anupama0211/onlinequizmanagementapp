package com.epam.dao;

import com.epam.entities.Option;
import com.epam.entities.Question;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class QuestionDAO {

    @Autowired
    EntityManager entityManager;


    public Question insert(Question question) {
        entityManager.getTransaction().begin();
        entityManager.persist(question);
        entityManager.getTransaction().commit();
        return question;
    }

    public Question getQuestion(int questionID) throws InvalidIDException {
        Optional<Question> questionOptional = Optional.ofNullable(entityManager.find(Question.class, questionID));
        if (questionOptional.isEmpty()) {
            throw new InvalidIDException("Invalid Question ID!!");
        }
        return questionOptional.get();
    }

    public Question remove(Question question){
        entityManager.getTransaction().begin();
        entityManager.remove(question);
        entityManager.getTransaction().commit();
        return question;
    }

    public Question update(Question oldQuestion, Question newQuestion) {
        oldQuestion.setDifficulty(newQuestion.getDifficulty());
        oldQuestion.setTopic(newQuestion.getTopic());
        Set<Option> oldOptions=oldQuestion.getOptions();
        oldOptions.forEach(option -> entityManager.remove(option));
        oldQuestion.setOptions(newQuestion.getOptions());
        oldQuestion.setTitle(newQuestion.getTitle());
        oldQuestion.setMarks(newQuestion.getMarks());
        insert(oldQuestion);
        return oldQuestion;
    }

    public List<Question> getAllQuestions()throws EmptyLibraryException {
        List<Question> questions = entityManager.createQuery("from Question").getResultList();
        if (questions.isEmpty()) {
            throw new EmptyLibraryException("Question Library is empty!!");
        }
        return questions;
    }
}
