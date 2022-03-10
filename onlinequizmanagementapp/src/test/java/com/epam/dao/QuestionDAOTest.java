package com.epam.dao;


import com.epam.entities.Option;
import com.epam.entities.Question;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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
        question.setOptions(Set.of(new Option(1, "Island", false)
                , new Option(1, "Coffee", true)));
    }

    @Test
    void insert() {
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionmock);
        questionDAO.insert(question);
        verify(entityManagerMock).persist(question);
    }


    void findQuestion() throws InvalidIDException {
        when(entityManagerMock.find(Question.class, 1)).thenReturn(question);
        assertEquals(question, questionDAO.getQuestion(1));
        verify(entityManagerMock).find(Question.class, 1);
    }

    @Test()
    void remove() {
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionmock);
        when(entityManagerMock.find(Question.class, 1)).thenReturn(question);
        assertEquals(question, questionDAO.remove(question));
        verify(entityManagerMock).remove(question);
    }

    @Test
    void update() {
        when(entityManagerMock.find(Question.class, 1)).thenReturn(question);
        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionmock);
        assertEquals(question,questionDAO.update(question, question));
    }

    @Test
    void getAllQuestions() throws EmptyLibraryException {
       when(entityManagerMock.createQuery("from Question")).thenReturn(query);
       when(query.getResultList()).thenReturn(List.of(question));
       assertEquals(List.of(question),questionDAO.getAllQuestions());
    }

}