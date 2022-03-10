package com.epam.services;

import com.epam.dao.QuestionDAO;
import com.epam.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.RollbackException;
import java.util.List;
import java.util.Optional;

@Component
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public Question addQuestion(Question question) {
        return questionDAO.insert(question);
    }

    public int removeQuestion(int questionId) {
        int status = 0;
        try {
            if (questionDAO.remove(questionId).isPresent()) {
                status = 1;
            }
        } catch (RollbackException e) {
            status = 2;
        }
        return status;
    }

    public boolean modifyQuestion(int questionId, Question question) {
        return questionDAO.update(questionId, question);
    }

    public Optional<List<Question>> getAllQuestions() {
        List<Question> questions = questionDAO.getAllQuestions();
        if (questions.isEmpty()) {
            questions = null;
        }
        return Optional.ofNullable(questions);
    }

    public Optional<Question> findQuestion(List<Question> questions, int questionId) {
        return questions
                .stream()
                .filter(question -> question.getQuestionId() == questionId)
                .findFirst();
    }
}
