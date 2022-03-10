//package com.epam.services;
//
//import com.epam.dao.QuestionDAO;
//import com.epam.entities.Option;
//import com.epam.entities.Question;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import javax.persistence.RollbackException;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class QuestionServiceTest {
//    @Mock
//    private static QuestionDAO questionDAOmock;
//
//    @InjectMocks
//    private static QuestionService questionService;
//
//    Question question;
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
//    }
//
//
//    @Test
//    void addQuestionTest() {
//        questionService.addQuestion(question);
//        verify(questionDAOmock, times(1)).insert(question);
//    }
//
//    @Test
//    void removeQuestionTest() {
//
//        when(questionDAOmock.remove(1)).thenReturn(Optional.ofNullable(question));
//        assertEquals(1, questionService.removeQuestion(1));
//        verify(questionDAOmock, times(1)).remove(1);
//
//
//        when(questionDAOmock.remove(3)).thenThrow(RollbackException.class);
//        assertEquals(2, questionService.removeQuestion(3));
//        verify(questionDAOmock, times(1)).remove(3);
//
//    }
//
//    @Test
//    void modifyQuestionTest() {
//        when(questionDAOmock.update(1, question)).thenReturn(true);
//        when(questionDAOmock.update(2, question)).thenReturn(false);
//
//        assertTrue(questionService.modifyQuestion(1, question));
//        assertFalse(questionService.modifyQuestion(2, question));
//
//        verify(questionDAOmock, times(2)).update(anyInt(), any(Question.class));
//    }
//
//    @Test
//    void getAllQuestionsTest() {
//        when(questionDAOmock.getAllQuestions()).thenReturn((List.of(question)));
//        assertEquals(Optional.of(List.of(question)), questionService.getAllQuestions());
//    }
//
//    @Test
//    void findQuestionTest() {
//        assertEquals(Optional.ofNullable(question),
//                questionService.findQuestion(List.of(question), 1));
//    }
//
//}
