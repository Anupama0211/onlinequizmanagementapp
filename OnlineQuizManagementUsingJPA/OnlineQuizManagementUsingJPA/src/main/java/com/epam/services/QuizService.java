package com.epam.services;


import com.epam.dao.QuestionDAO;
import com.epam.dao.QuizDAO;
import com.epam.entities.Question;
import com.epam.entities.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class QuizService {
    QuizDAO quizDAO;

    public QuizService(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }

    public Optional<Quiz> viewAQuiz(int quizId) {
        return quizDAO.findQuiz(quizId);
    }

    public int insertQuiz(Quiz quiz) {
        return quizDAO.insertQuiz(quiz);
    }

    public boolean deleteQuiz(int quizId) {
        return quizDAO.delete(quizId);
    }

    public List<String> viewQuizTitles() {
        List<String> quizTitles = new ArrayList<>();
        Optional<List<Quiz>> optionalQuizzes = quizDAO.readQuizzes();
        if (optionalQuizzes.isPresent()) {
            List<Quiz> quizzes = optionalQuizzes.get();
            quizTitles = quizzes.stream()
                    .map(quiz -> "ID: " + quiz.getQuizId() + "\n" + quiz.getTitle() + "\n--------------------------")
                    .toList();
        }
        return quizTitles;
    }

    public Optional<Question> getQuestionInAQuiz(Quiz quiz, int questionId) {
        return quiz.getQuestions().stream().filter(question -> question.getQuestionId() == questionId).findFirst();
    }

    public boolean deleteQuestionInQuiz(int quizId, int questionId) {
        boolean status = false;
        Optional<Quiz> quizOptional = viewAQuiz(quizId);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            Optional<Question> questionOptional = getQuestionInAQuiz(quiz, questionId);
            if (questionOptional.isPresent()) {
                Question question = questionOptional.get();
                quiz.getQuestions().remove(question);
                insertQuiz(quiz);
                status = true;
            }
        }
        return status;
    }

    public boolean addQuestionFromQuestionLibrary(int quizId, int questionId) {
        boolean status = false;
        Optional<Quiz> quizOptional = viewAQuiz(quizId);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            Optional<Question> questionOptional = new QuestionService(new QuestionDAO()).findQuestion(questionId);
            if (questionOptional.isPresent()) {
                quiz.getQuestions().add(questionOptional.get());
                insertQuiz(quiz);
                status = true;
            }
        }
        return status;
    }

    public boolean addQuetsionInQuizOnYourOwn(int quizId, Question question) {
        boolean status = false;
        Optional<Quiz> quizOptional = viewAQuiz(quizId);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            new QuestionService(new QuestionDAO()).addQuestion(question);
            Question questionInQuiz = new QuestionService(new QuestionDAO())
                    .findQuestionByTitle(question.getTitle());
            quiz.getQuestions().add(questionInQuiz);
            insertQuiz(quiz);
            status = true;
        }
        return status;
    }
}
