package com.epam.services;

import com.epam.dao.QuestionDAO;
import com.epam.dao.QuizDAO;
import com.epam.entities.Option;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    QuizDAO quizDAOmock;

    @InjectMocks
    QuizService quizService;


    Question question;
    Quiz quiz;

    @BeforeEach
    void setUp() {
        question = new Question();
        question.setQuestionId(1);
        question.setDifficulty("Easy");
        question.setTitle("What is JAVA");
        question.setTopic("Programming");
        question.setMarks(2);
        question.setOptions(List.of(new Option(1, "Island", false)
                , new Option(1, "Coffee", true)));

        quiz = new Quiz();
        quiz.setQuizId(111);
        Set<Question> questions = new HashSet<>();
        questions.add(question);
        quiz.setQuestions(questions);
        quiz.setTitle("DEMO");
    }


    @Test
    void getAQuizTest() {
        when(quizDAOmock.getAQuiz(111)).thenReturn(Optional.ofNullable(quiz));
        assertEquals(Optional.ofNullable(quiz), quizService.getAQuiz(111));
    }

    @Test
    void insertQuizTest() {
        when(quizDAOmock.insertQuiz(quiz)).thenReturn(quiz);
        assertEquals(quiz, quizService.insertQuiz(quiz));
    }

    @Test
    void deleteQuizTest() {
        when(quizDAOmock.delete(111)).thenReturn(true);
        when(quizDAOmock.delete(123)).thenReturn(false);
        assertTrue(quizService.deleteQuiz(111));
        assertFalse(quizService.deleteQuiz(123));
    }

    @Test
    void getAllQuizzesTest() {
        quizService.getAllQuizzes();
        verify(quizDAOmock, times(1)).getAllQuizzes();
    }

    @Test
    void findQuizTest() {
        assertEquals(Optional.ofNullable(quiz), quizService.findQuiz(List.of(quiz), 111));
    }

    @Test
    void viewQuizTitlesTest() {
        List<String> titles = List.of(quiz).stream()
                .map(quiz -> "ID: " + quiz.getQuizId() + "\n" + quiz.getTitle() + "\n--------------------------")
                .toList();
        assertEquals(titles, quizService.viewQuizTitles(List.of(quiz)));
    }

    @Test
    void getQuestionInAQuizTest() {

        assertEquals(quiz.getQuestions()
                        .stream()
                        .filter(question -> question.getQuestionId() == 1).findFirst()
                , quizService.getQuestionInAQuiz(Optional.ofNullable(quiz), 1));

    }

    @Test
    void deleteQuestionInQuizTest() {
        quizService.deleteQuestionInQuiz(quiz, Optional.ofNullable(question));
        assertEquals(0, quiz.getQuestions().size());
        verify(quizDAOmock).insertQuiz(any(Quiz.class));
    }

    @Test
    void addQuestionFromQuestionLibraryTest() {
        quiz.getQuestions().remove(question);
        quizService.addQuestionFromQuestionLibrary(Optional.ofNullable(quiz), Optional.ofNullable(question));
        assertEquals(1, quiz.getQuestions().size());
        verify(quizDAOmock).insertQuiz(any(Quiz.class));
    }

    @Test
    void addQuetsionInQuizOnYourOwnTest() {
        QuestionDAO questionDAOmock = mock(QuestionDAO.class);
        QuestionService questionService = new QuestionService(questionDAOmock);
        quiz.getQuestions().remove(question);
        quizService.addQuestionFromQuestionLibrary(Optional.ofNullable(quiz), Optional.ofNullable(question));
        assertEquals(1, quiz.getQuestions().size());
        verify(quizDAOmock).insertQuiz(any(Quiz.class));
    }
}