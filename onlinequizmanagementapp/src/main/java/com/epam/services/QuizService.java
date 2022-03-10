package com.epam.services;


import com.epam.dao.QuizDAO;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuizService {
    @Autowired
    QuizDAO quizDAO;
    @Autowired
    QuestionService questionService;

    public QuizService(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }

    public Quiz getAQuiz(int quizId) throws InvalidIDException {
        return quizDAO.getAQuiz(quizId);
    }

    public Quiz insertQuiz(Quiz quiz) {
        return quizDAO.insertQuiz(quiz);
    }

    public void deleteQuiz(Quiz quiz) {
        quizDAO.delete(quiz);
    }

    public List<Quiz> getAllQuizzes() throws EmptyLibraryException {
        return quizDAO.getAllQuizzes();
    }

    public Quiz findQuiz(List<Quiz> quizzes, int quizId) throws InvalidIDException {
        Optional<Quiz> quizOptional = quizzes
                .stream()
                .filter(quiz -> quiz.getQuizId() == quizId)
                .findFirst();
        if (quizOptional.isEmpty()) {
            throw new InvalidIDException("Invalid Quiz ID!!");
        }
        return quizOptional.get();
    }

    public List<String> viewQuizTitles(List<Quiz> quizzes) {
        return quizzes.stream()
                .map(quiz -> "ID: " + quiz.getQuizId() + "\n" + quiz.getTitle() + "\n--------------------------")
                .toList();
    }

    public Question getQuestionInAQuiz(Quiz quiz, int questionId) throws InvalidIDException {
        Optional<Question> questionOptional =  quiz
                .getQuestions()
                .stream()
                .filter(question -> question.getQuestionId() == questionId)
                .findFirst();
        if (questionOptional.isEmpty()) {
            throw new InvalidIDException("Such a Question ID does not exist in the Quiz!!!");
        }
        return questionOptional.get();

    }

    public void deleteQuestionInQuiz(Quiz quiz, Question question) {
        quiz.getQuestions().remove(question);
        quizDAO.insertQuiz(quiz);
}

    public void addQuestionFromQuestionLibrary(Quiz quiz, Question question) {
            quiz.getQuestions().add(question);
            quizDAO.insertQuiz(quiz);
    }

    public void addQuetsionInQuizOnYourOwn(Quiz quiz, Question question) {
        Question questionToBeAddedInQuiz = questionService.addQuestion(question);
        quiz.getQuestions().add(questionToBeAddedInQuiz);
        insertQuiz(quiz);
    }
}
