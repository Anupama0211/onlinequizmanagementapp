package com.epam.services;


import com.epam.dao.QuestionRepository;
import com.epam.dto.OptionDto;
import com.epam.dto.QuestionDto;
import com.epam.entities.Option;
import com.epam.entities.Question;
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

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {
    @Mock
    private static QuestionRepository questionRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    private static QuestionService questionService;


    QuestionDto questionDto;


    Question question;
    List<Option> options;
    List<OptionDto> optionDtos;

    @BeforeEach
    void setUp() {

        questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setDifficulty("Easy");
        questionDto.setTitle("What is JAVA");
        questionDto.setTopic("Programming");
        questionDto.setMarks(2);

        question = new Question();
        question.setQuestionId(1);
        question.setDifficulty("Easy");
        question.setTitle("What is JAVA");
        question.setTopic("Programming");
        question.setMarks(2);
        options = new ArrayList<>();
        optionDtos = new ArrayList<>();
        options.addAll(List.of(new Option(1, "Island", false)
                , new Option(1, "Coffee", true)));
        question.setOptions(options);
        optionDtos.addAll(List.of(new OptionDto(1, "Island", false)
                , new OptionDto(1, "Coffee", true)));
        questionDto.setOptions(optionDtos);
    }


    @Test
    void addQuestionTest() {
        when(questionRepository.save(question)).thenReturn(question);
        when(modelMapper.map(questionDto, Question.class)).thenReturn(question);
        when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);
        assertThat(questionService.addQuestion(questionDto)).isEqualTo(questionDto);
        verify(questionRepository).save(question);
    }

    @Test
    void removeQuestionTest() {
        questionService.removeQuestion(1);
        verify(questionRepository, times(1)).deleteById(1);
    }

    @Test
    void getQuestionById() throws InvalidIDException {
        when(questionRepository.findById(1)).thenReturn(Optional.ofNullable(question));
        when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);
        assertThat(questionService.getQuestionByID(1)).isEqualTo(questionDto);
        when(questionRepository.findById(2)).thenReturn(Optional.ofNullable(null));
        assertThrows(InvalidIDException.class, () -> questionService.getQuestionByID(2));
    }

    @Test
    void modifyQuestionTest() throws InvalidIDException {
        when(questionRepository.save(question)).thenReturn(question);
        question.setOptions(options);
        when(questionRepository.findById(1)).thenReturn(Optional.ofNullable(question));
        when(modelMapper.map(question, QuestionDto.class)).thenReturn(questionDto);
        assertThat(questionService.modifyQuestion(questionDto,1)).isEqualTo(questionDto);
        verify(questionRepository).save(question);
    }
    @Test
    void modifyQuestionTestWhenIdIsWrong() throws InvalidIDException {
        when(questionRepository.findById(1)).thenReturn(Optional.ofNullable(null));
        assertThrows(InvalidIDException.class,()->questionService.modifyQuestion(questionDto,1));
    }

    @Test
    void getAllQuestionsTest() throws EmptyLibraryException {
        when(questionRepository.findAll()).thenReturn((List.of(question)));
        when(modelMapper.map(List.of(question), new TypeToken<List<QuestionDto>>() {
        }.getType())).thenReturn(List.of(questionDto));
        assertThat(questionService.getAllQuestions()).isEqualTo(List.of(questionDto));
        when(questionRepository.findAll()).thenReturn((List.of()));
        assertThrows(EmptyLibraryException.class, () -> questionService.getAllQuestions());
    }
}
