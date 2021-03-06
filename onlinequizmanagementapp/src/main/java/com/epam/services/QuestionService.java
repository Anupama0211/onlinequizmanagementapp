package com.epam.services;


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
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QuestionService {


    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ModelMapper modelMapper;

    public QuestionDto addQuestion(QuestionDto questionDto) {
        List<OptionDto> options = questionDto.getOptions()
                .stream()
                .filter(option -> !(option.getValue().isEmpty()))
                .toList();

        questionDto.setOptions(options);
        Question question = questionRepository.save(modelMapper.map(questionDto, Question.class));
        return modelMapper.map(question, QuestionDto.class);
    }

    public void removeQuestion(int questionId) {
        questionRepository.deleteById(questionId);
    }

    public QuestionDto getQuestionByID(int questionId) {
        return modelMapper
                .map(questionRepository
                                .findById(questionId)
                                .orElseThrow(() -> new InvalidIDException("Invalid Question ID!!"))
                        , QuestionDto.class);

    }

    public QuestionDto modifyQuestion(QuestionDto newQuestionDto, int questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            question.setMarks(newQuestionDto.getMarks());
            question.setTopic(newQuestionDto.getTopic());
            question.setDifficulty(newQuestionDto.getDifficulty());
            question.setTitle(newQuestionDto.getTitle());
            List<OptionDto> newOptions = newQuestionDto.getOptions();
            List<Option> oldOptions = question.getOptions();
            for (int i = 0; i < oldOptions.size(); i++) {
                oldOptions.get(i).setValue(newOptions.get(i).getValue());
                oldOptions.get(i).setAnswer(newOptions.get(i).isAnswer());
            }
            return modelMapper.map(questionRepository.save(question), QuestionDto.class);
        }
        throw new InvalidIDException("Invalid Question ID!!");
    }

    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = (List<Question>) questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new EmptyLibraryException("Question Library is empty!!");
        }
        return modelMapper.map(questions, new TypeToken<List<QuestionDto>>() {
        }.getType());
    }
}
