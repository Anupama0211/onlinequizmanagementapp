package com.epam.dao;

import com.epam.entities.Quiz;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Component
public class QuizDAO {

    @Autowired
    EntityManager entityManager;

    public QuizDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public Quiz insertQuiz(Quiz quiz) {
        entityManager.getTransaction().begin();
        entityManager.persist(quiz);
        entityManager.getTransaction().commit();
        return quiz;
    }

    public Quiz getAQuiz(int quizId) throws InvalidIDException {
        Optional<Quiz> quizOptional = Optional.
                ofNullable(entityManager.find(Quiz.class, quizId));
        if (quizOptional.isEmpty()) {
            throw new InvalidIDException("Invalid Quiz ID");
        }
        return quizOptional.get();
    }

    public void delete(Quiz quiz) {
        entityManager.getTransaction().begin();
        entityManager.remove(quiz);
        entityManager.getTransaction().commit();
    }

    public List<Quiz> getAllQuizzes()throws EmptyLibraryException {
        List<Quiz> quizzes=entityManager.createQuery("from Quiz").getResultList();
        if(quizzes.isEmpty()){
            throw new EmptyLibraryException("Quiz Library is empty!!!");
        }
        return quizzes;
    }
}
