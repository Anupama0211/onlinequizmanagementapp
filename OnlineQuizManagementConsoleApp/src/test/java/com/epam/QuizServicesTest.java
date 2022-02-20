package com.epam;

import com.epam.database.QuizDatabase;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.questionservices.QuestionBuilder;
import com.epam.services.quizservices.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizServicesTest {
    Question question;
    Quiz quiz;

    @BeforeEach
    void setUp() {
        question = new QuestionBuilder()
                .setTitle("Capital Of India")
                .setOptions(new String[]{"Kolkata", "Delhi", "Hyderabad", "Bhopal"})
                .setAnswer("Delhi")
                .setTopic("GK")
                .setDifficulty("Easy")
                .setMarks(2)
                .build();
        quiz = new Quiz();
    }

    @Test
    void addQuestionInQuizTest() {
        assertFalse(new AddQuestionInQuiz().perform(1, null));
        assertTrue(new AddQuestionInQuiz().perform(1, question));
    }

    @Test
    void addQuizTest() {
        assertTrue(new AddQuiz().perform(quiz));
        new RemoveQuiz().perform(2);
        assertFalse(new AddQuiz().perform(null));

    }

    @Test
    void quizListSizeTest() {
        assertEquals(QuizDatabase.size(), new QuizListSize().get());
    }

    @Test
    void viewAQuizTest() {
        assertEquals(QuizDatabase.getQuiz(0), new ViewAQuiz().perform(1));
    }

    @Test
    void removeQuestionInQuizTest() {
        assertTrue(new RemoveQuestionInQuiz().perform(1, 1));
        assertFalse(new RemoveQuestionInQuiz().perform(0, 1));
        assertFalse(new RemoveQuestionInQuiz().perform(1, 0));
        assertFalse(new RemoveQuestionInQuiz().perform(0, 0));
    }

    @Test
    void quizTitlesTest() {
        assertEquals("1. Default Java Quiz\n", new QuizTitles().get());
    }

    @Test
    void removeQuizTest() {
        assertTrue(new RemoveQuiz().perform(1));
        assertFalse(new RemoveQuiz().perform(QuizDatabase.size() + 1));
    }

}
