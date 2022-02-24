package com.epam.dao;

import com.epam.entities.Question;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class QuestionDAO {
    EntityManager entityManager = GetManager.getEntityManger();

    public void insert(Question question) {
        entityManager.getTransaction().begin();
        entityManager.persist(question);
        entityManager.getTransaction().commit();
    }
    public  Optional<Question> findQuestion(int questionID){
     return  Optional
             .ofNullable(entityManager.find(Question.class, questionID));
    }

    public Optional<Question> remove(int questionId) {
        Optional<Question> questionOptional =findQuestion(questionId);
        if (questionOptional.isPresent()) {
            entityManager.getTransaction().begin();
            entityManager.remove(questionOptional.get());
            entityManager.getTransaction().commit();
        }
        return questionOptional;
    }

    public boolean update(int questionId, Question question) {
        boolean modifiedStatus = false;
        Optional<Question> questionOptional = findQuestion(questionId);
        if (questionOptional.isPresent()) {
            modifiedStatus = true;
            Question questionFromDB = questionOptional.get();
            questionFromDB.setDifficulty(question.getDifficulty());
            questionFromDB.setTopic(question.getTopic());
            questionFromDB.setOptions(question.getOptions());
            questionFromDB.setTitle(question.getTitle());
            questionFromDB.setMarks(question.getMarks());
            insert(questionFromDB);
        }
        return modifiedStatus;
    }

    public List<Question> read() {
        List<Question> questions = entityManager.createQuery("from Question").getResultList();
        return questions;
    }
    public void close(){
        entityManager.close();
    }
}
