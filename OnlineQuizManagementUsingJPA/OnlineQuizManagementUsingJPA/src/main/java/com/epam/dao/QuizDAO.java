package com.epam.dao;

import com.epam.entities.Quiz;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class QuizDAO {
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

    public Optional<Quiz> getAQuiz(int quizId) {
        return Optional.
                ofNullable(entityManager.find(Quiz.class, quizId));
    }

    public boolean delete(int quizId) {
        boolean status = false;
        Optional<Quiz> quizOptional = getAQuiz(quizId);
        if (quizOptional.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(quizOptional.get());
            entityManager.getTransaction().commit();
            status = true;
        }
        return status;
    }

    public Optional<List<Quiz>> getAllQuizzes() {
        return Optional
                .ofNullable(entityManager.createQuery("from Quiz").getResultList());
    }
}
