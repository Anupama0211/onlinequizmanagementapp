package com.epam.services;

import com.epam.dao.QuestionDAO;
import com.epam.entities.Question;

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

    public Optional<Question> removeQuestion(int questionId) {
        return questionDAO.remove(questionId);
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
    public  Optional<Question> findQuestion(int questionID){
        return  questionDAO.findQuestion(questionID);
    }
}
