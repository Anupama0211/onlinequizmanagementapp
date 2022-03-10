package com.epam.services;

import com.epam.dao.QuestionDAO;
import com.epam.entities.Question;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
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

    public Question removeQuestion(Question question) throws RollbackException {
            return questionDAO.remove(question);
    }

    public Question modifyQuestion(Question oldQuestion, Question newQuestion){
        return questionDAO.update(oldQuestion, newQuestion);
    }

    public List<Question> getAllQuestions() throws EmptyLibraryException {
        return questionDAO.getAllQuestions();
    }

    public Question findQuestion(List<Question> questions, int questionId) throws InvalidIDException {
        Optional<Question> questionOptional = questions
                .stream()
                .filter(question -> question.getQuestionId() == questionId)
                .findFirst();
        if (questionOptional.isEmpty()) {
            throw new InvalidIDException("Invalid Question ID!!!");
        }
        return questionOptional.get();
    }
}
