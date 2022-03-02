package com.epam.services;


import com.epam.dao.GetManager;
import com.epam.dao.QuestionDAO;
import com.epam.dao.QuizDAO;
import com.epam.entities.Question;
import com.epam.entities.Quiz;

import java.util.List;
import java.util.Optional;


public class QuizService {
    QuizDAO quizDAO;

    public QuizService(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }

    public Optional<Quiz> getAQuiz(int quizId) {
        return quizDAO.getAQuiz(quizId);
    }

    public Quiz insertQuiz(Quiz quiz) {
        return quizDAO.insertQuiz(quiz);
    }

    public boolean deleteQuiz(int quizId) {
        return quizDAO.delete(quizId);
    }

    public Optional<List<Quiz>> getAllQuizzes() {
        return quizDAO.getAllQuizzes();
    }

    public Optional<Quiz> findQuiz(List<Quiz> quizzes, int quizId) {
        return quizzes
                .stream()
                .filter(quiz -> quiz.getQuizId() == quizId)
                .findFirst();
    }

    public List<String> viewQuizTitles(List<Quiz> quizzes) {
        return quizzes.stream()
                .map(quiz -> "ID: " + quiz.getQuizId() + "\n" + quiz.getTitle() + "\n--------------------------")
                .toList();
    }

    public Optional<Question> getQuestionInAQuiz(Optional<Quiz> quizOptional, int questionId) {
       Optional<Question> questionOptional=Optional.ofNullable(null);
        if (quizOptional.isPresent()) {
            questionOptional= quizOptional
                    .get()
                    .getQuestions()
                    .stream()
                    .filter(question -> question.getQuestionId() == questionId)
                    .findFirst();
        }
        return questionOptional;
    }

    public boolean deleteQuestionInQuiz(Quiz quiz,Optional<Question> questionOptional) {
        boolean status = false;
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            quiz.getQuestions().remove(question);
            quizDAO.insertQuiz(quiz);
            status = true;
        }
        return status;
    }

    public boolean addQuestionFromQuestionLibrary(Optional<Quiz> quizOptional, Optional<Question> questionOptional) {
        boolean status = false;
        if (quizOptional.isPresent() && questionOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            quiz.getQuestions().add(questionOptional.get());
            quizDAO.insertQuiz(quiz);
            status = true;
        }
        return status;
    }

    public boolean addQuetsionInQuizOnYourOwn(Optional<Quiz> quizOptional, Question question) {
        boolean status = false;
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            Question questionToBeAddedInQuiz = new QuestionService(new QuestionDAO(GetManager.getEntityManger())).addQuestion(question);
            quiz.getQuestions().add(questionToBeAddedInQuiz);
            insertQuiz(quiz);
            status = true;
        }
        return status;
    }
}
