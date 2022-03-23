package com.epam.services;


import com.epam.dao.QuestionRepository;
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
    ModelMapper modelMapper;

    public QuestionDto addQuestion(QuestionDto questionDto) {
        List<Option> options = questionDto.getOptions().stream().filter(option -> !(option.getValue().isEmpty())).toList();
        questionDto.setOptions(options);
        Question question = questionRepository.save(modelMapper.map(questionDto, Question.class));
        return modelMapper.map(question, QuestionDto.class);
    }

    public void removeQuestion(int questionId) throws DataIntegrityViolationException {
        questionRepository.deleteById(questionId);
    }

    public QuestionDto getQuestionByID(int questionId) throws InvalidIDException {
        return modelMapper
                .map(questionRepository
                                .findById(questionId)
                                .orElseThrow(() -> new InvalidIDException("Invalid Question ID!!"))
                        , QuestionDto.class);

    }

    public QuestionDto modifyQuestion(QuestionDto newQuestionDto) throws InvalidIDException {
        Optional<Question> questionOptional = questionRepository.findById(newQuestionDto.getQuestionId());
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            question.setQuestionId(newQuestionDto.getQuestionId());
            question.setMarks(newQuestionDto.getMarks());
            question.setTopic(newQuestionDto.getTopic());
            question.setDifficulty(newQuestionDto.getDifficulty());
            question.setTitle(newQuestionDto.getTitle());
            Iterator<Option> newOptionIterator = newQuestionDto.getOptions().iterator();
            Iterator<Option> oldOptionIterator = question.getOptions().iterator();
            while (newOptionIterator.hasNext() && oldOptionIterator.hasNext()) {
                Option oldOption = oldOptionIterator.next();
                Option newOption = newOptionIterator.next();
                oldOption.setValue(newOption.getValue());
                oldOption.setAnswer(newOption.isAnswer());
            }
            newQuestionDto = modelMapper.map(questionRepository.save(question), QuestionDto.class);
        } else {
            throw new InvalidIDException("Invalid Question ID!!");
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
