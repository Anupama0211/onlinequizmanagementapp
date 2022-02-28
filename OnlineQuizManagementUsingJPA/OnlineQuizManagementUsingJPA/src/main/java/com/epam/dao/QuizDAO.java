package com.epam.dao;

import com.epam.entities.Quiz;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class QuizDAO {
    EntityManager entityManager=GetManager.getEntityManger();

    public int insertQuiz(Quiz quiz) {
            entityManager.getTransaction().begin();
            entityManager.persist(quiz);
            entityManager.getTransaction().commit();
            return quiz.getQuizId();
    }

    public Optional<Quiz> findQuiz(int quizId) {
        return Optional.
                ofNullable(entityManager.find(Quiz.class, quizId));
    }

    public boolean delete(int quizId) {
        boolean status = false;
        Optional<Quiz> quizOptional = findQuiz(quizId);
        if (quizOptional.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(quizOptional.get());
            entityManager.getTransaction().commit();
            status = true;
        }
        return status;
    }

    public Optional<List<Quiz>> readQuizzes() {
        return Optional
                .ofNullable(entityManager.createQuery("from Quiz").getResultList());
    }

    public boolean update(int quizId, Quiz quiz) {
        boolean modifiedStatus = false;
        Optional<Quiz> quizOptional = findQuiz(quizId);
        if (quizOptional.isPresent()) {
            modifiedStatus = true;
            Quiz quizFromDB = quizOptional.get();
            quizFromDB.setQuestions(quiz.getQuestions());
            quizFromDB.setTitle(quiz.getTitle());
            insertQuiz(quizFromDB);
        }
        return modifiedStatus;
    }

    public void close() {
        entityManager.close();
    }

}
