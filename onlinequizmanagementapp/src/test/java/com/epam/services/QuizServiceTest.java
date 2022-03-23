package com.epam.services;

import com.epam.dao.QuizRepository;
import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.entities.Option;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    QuizRepository quizRepository;
    @Mock
    QuestionService questionService;
    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    QuizService quizService;


    Question question;
    Quiz quiz;
    QuizDto quizDto;
    QuestionDto questionDto;

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

        questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setDifficulty("Easy");
        questionDto.setTitle("What is JAVA");
        questionDto.setTopic("Programming");
        questionDto.setMarks(2);
        questionDto.setOptions(List.of(new Option(1, "Island", false)
                , new Option(1, "Coffee", true)));

        quizDto = new QuizDto();
        quizDto.setQuizId(111);
        quizDto.setQuestions(questions);
        quizDto.setTitle("DEMO");
    }


    @Test
    void getAQuizTest() throws InvalidIDException {

        when(quizRepository.findById(111)).thenReturn(Optional.ofNullable(quiz));
        when(modelMapper.map(quiz, QuizDto.class)).thenReturn(quizDto);
        assertThat(quizService.getAQuiz(111)).isEqualTo(quizDto);
        when(quizRepository.findById(112)).thenReturn(Optional.ofNullable(null));
        assertThrows(InvalidIDException.class, () -> quizService.getAQuiz(112));
    }

    @Test
    void insertQuizTest() throws InvalidIDException {
        when(quizRepository.findById(111)).thenReturn(Optional.ofNullable(quiz));
        when(modelMapper.map(quizDto, Quiz.class)).thenReturn(quiz);
        when(questionService.getQuestionByID(1)).thenReturn(questionDto);
        when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
        when(quizRepository.save(quiz)).thenReturn(quiz);
        when(modelMapper.map(quiz, QuizDto.class)).thenReturn(quizDto);
        assertThat(quizService.insertQuiz(quizDto, List.of(1))).isEqualTo(quizDto);
        quiz.setQuestions(null);
        assertThat(quizService.insertQuiz(quizDto, List.of(1))).isEqualTo(quizDto);

    }

    @Test
    void deleteQuizTest() {
        quizService.deleteQuiz(111);
        verify(quizRepository).deleteById(111);
    }

    @Test
    void getAllQuizzesTest() throws EmptyLibraryException {
        when(quizRepository.findAll()).thenReturn(List.of(quiz));
        when(modelMapper.map(List.of(quiz), new TypeToken<List<QuizDto>>() {
        }.getType())).thenReturn(List.of(quizDto));
        assertThat(quizService.getAllQuizzes()).isEqualTo(List.of(quizDto));
        when(quizRepository.findAll()).thenReturn(List.of());
        assertThrows(EmptyLibraryException.class, () -> quizService.getAllQuizzes());
    }


    @Test
    void deleteQuestionInQuizTest() throws InvalidIDException {
        when(quizRepository.findById(111)).thenReturn(Optional.ofNullable(quiz));
        quizService.deleteQuestionInQuiz(111, 1);
        assertThat(quiz.getQuestions().isEmpty());
        verify(quizRepository).save(quiz);
        when(quizRepository.findById(111)).thenReturn(Optional.ofNullable(null));
        assertThrows(InvalidIDException.class, () -> quizService.deleteQuestionInQuiz(111, 1));
    }


}