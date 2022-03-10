//package com.epam.dao;
//
//import com.epam.entities.Option;
//import com.epam.entities.Question;
//import com.epam.entities.Quiz;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Query;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class QuizDAOTest {
//
//    @Mock
//    EntityManager entityManagerMock;
//    @InjectMocks
//    QuizDAO quizDAO;
//    @Mock
//    EntityTransaction entityTransactionMock;
//    @Mock
//    Query query;
//    Question question;
//    Quiz quiz;
//
//    @BeforeEach
//    void setUp() {
//        question = new Question();
//        question.setQuestionId(1);
//        question.setDifficulty("Easy");
//        question.setTitle("What is JAVA");
//        question.setTopic("Programming");
//        question.setMarks(2);
//        question.setOptions(List.of(new Option(1, "Island", false)
//                , new Option(1, "Coffee", true)));
//
//        quiz = new Quiz();
//        quiz.setQuizId(111);
//        Set<Question> questions = new HashSet<>();
//        questions.add(question);
//        quiz.setQuestions(questions);
//        quiz.setTitle("DEMO");
//    }
//
//    @Test
//    void insertQuiz() {
//        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
//        quizDAO.insertQuiz(quiz);
//        verify(entityManagerMock).persist(quiz);
//    }
//
//
//    @Test
//    void getAQuiz() {
//        when(entityManagerMock.find(Quiz.class, 111)).thenReturn(quiz);
//        assertEquals(Optional.ofNullable(quiz), quizDAO.getAQuiz(111));
//    }
//
//    @Test
//    void delete() {
//        when(entityManagerMock.find(Quiz.class, 111)).thenReturn(quiz);
//        when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
//        assertTrue(quizDAO.delete(111));
//        verify(entityManagerMock).remove(quiz);
//    }
//
//    @Test
//    void getAllQuizzes() {
//        when(entityManagerMock.createQuery("from Quiz")).thenReturn(query);
//        when(query.getResultList()).thenReturn(List.of(quiz));
//        assertEquals(List.of(quiz),quizDAO.getAllQuizzes());
//
//    }
//
//}