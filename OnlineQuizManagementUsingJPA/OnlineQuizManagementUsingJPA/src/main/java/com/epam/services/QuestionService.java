package com.epam.services;

import com.epam.dao.QuestionDAO;
import com.epam.entities.Question;

import javax.persistence.RollbackException;
import java.util.List;
import java.util.Optional;

public class QuestionService {
    QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public void addQuestion(Question question) {
        questionDAO.insert(question);
    }

    public int removeQuestion(int questionId) {
        int status = 0;
        try {
            if (questionDAO.remove(questionId).isPresent()) {
                status = 1;
            }
        } catch (RollbackException e) {
            status=2;
        }
        return status;
    }

    public boolean modifyQuestion(int questionId, Question question) {
        return questionDAO.update(questionId, question);
    }

    public List<Question> readQuestions() {
        return questionDAO.read();
    }

    public int size() {
        return questionDAO.read().size();
    }

    public Question findQuestionByTitle(String questionTitle) {
        return questionDAO.findQuestionByTitle(questionTitle);
    }

    public Optional<Question> findQuestion(int questionID) {
        return questionDAO.findQuestion(questionID);
    }
}
