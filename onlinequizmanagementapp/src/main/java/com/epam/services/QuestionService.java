package com.epam.services;


import com.epam.dao.OptionRepository;
import com.epam.dao.QuestionRepository;
import com.epam.dto.OptionDto;
import com.epam.dto.QuestionDto;
import com.epam.entities.Option;
import com.epam.entities.Question;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QuestionService {


    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    ModelMapper modelMapper;


    public QuestionDto addQuestion(QuestionDto questionDto, Set<OptionDto> optionDtos) {
        Set<Option> optionSet = modelMapper.map(optionDtos, new TypeToken<Set<Option>>() {
        }.getType());
        questionDto.setOptions(optionSet);
        Question question = modelMapper.map(questionDto, Question.class);
        question = questionRepository.save(question);
        return modelMapper.map(question, QuestionDto.class);
    }

    public void removeQuestion(int questionId) throws DataIntegrityViolationException {
        questionRepository.deleteById(questionId);
    }

    public QuestionDto getQuestionByID(int questionId) throws InvalidIDException {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()) {
            throw new InvalidIDException("Invalid Question ID!!");
        }
        return modelMapper.map(questionOptional.get(), QuestionDto.class);
    }

    public QuestionDto modifyQuestion(QuestionDto newQuestionDto, Set<OptionDto> optionDtos) {
        Optional<Question> questionOptional = questionRepository.findById(newQuestionDto.getQuestionId());
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            question.setQuestionId(newQuestionDto.getQuestionId());
            question.setMarks(newQuestionDto.getMarks());
            question.setTopic(newQuestionDto.getTopic());
            question.setDifficulty(newQuestionDto.getDifficulty());
            Iterator<OptionDto> optionDtoIterator = optionDtos.iterator();
            Iterator<Option> optionIterator = question.getOptions().iterator();
            while (optionDtoIterator.hasNext() && optionIterator.hasNext()) {
                Option option = optionIterator.next();
                OptionDto optionDto = optionDtoIterator.next();
                option.setValue(optionDto.getValue());
                option.setAnswer(optionDto.isAnswer());
            }
            newQuestionDto = modelMapper.map(questionRepository.save(question), QuestionDto.class);
        }
        return newQuestionDto;
    }

    public List<QuestionDto> getAllQuestions() throws EmptyLibraryException {
        List<Question> questions = (List<Question>) questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new EmptyLibraryException("Question Library is empty!!");
        }
        return modelMapper.map(questions, new TypeToken<List<QuestionDto>>() {
        }.getType());
    }
}
