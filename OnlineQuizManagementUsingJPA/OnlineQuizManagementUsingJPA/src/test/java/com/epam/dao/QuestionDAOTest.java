package com.epam.dao;


import com.epam.entities.Option;
import com.epam.entities.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.*;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class QuestionDAOTest {


    @Mock
    EntityManager entityManagerMock;
    @InjectMocks
    QuestionDAO questionDAO;
    Question question;
    @Mock
    EntityTransaction entityTransactionmock;
    @Mock Query query;

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
    }

    @Test
    void insert() {
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionmock);
        questionDAO.insert(question);
        verify(entityManagerMock).persist(question);
    }

    @Test
    void findQuestion() {
        when(entityManagerMock.find(Question.class, 1)).thenReturn(question);
        assertEquals(Optional.ofNullable(question), questionDAO.findQuestion(1));
        verify(entityManagerMock).find(Question.class, 1);
    }

    @Test
    void remove() {
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionmock);
        when(entityManagerMock.find(Question.class, 1)).thenReturn(question);
        assertEquals(Optional.ofNullable(question), questionDAO.remove(1));
        verify(entityManagerMock).remove(question);
    }

    @Test
    void update() {
        when(entityManagerMock.find(Question.class, 1)).thenReturn(question);
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionmock);
        assertTrue(questionDAO.update(1, question));
    }

    @Test
    void getAllQuestions() {
       when(entityManagerMock.createQuery("from Question")).thenReturn(query);
       when(query.getResultList()).thenReturn(List.of(question));
       assertEquals(Optional.ofNullable(List.of(question)),questionDAO.getAllQuestions());
    }

}