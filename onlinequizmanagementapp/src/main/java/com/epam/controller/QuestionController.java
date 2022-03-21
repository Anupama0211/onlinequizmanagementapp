package com.epam.controller;

import com.epam.dto.OptionDto;
import com.epam.dto.QuestionDto;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class QuestionController {

    private static final String MESSAGE = "message";
    @Autowired
    QuestionService questionService;

    @RequestMapping("questionLibraryPortal")
    public String questionLibraryPortal() {
        return "questionLibraryPortal";
    }

    @RequestMapping("createQuestion")
    public String createQuestion() {
        return "createQuestion";
    }

    @RequestMapping("viewQuestions")
    public ModelAndView viewQuestions() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<QuestionDto> questionDtos = questionService.getAllQuestions();
            modelAndView.addObject("questions", questionDtos);
            modelAndView.setViewName("viewQuestions");
        } catch (EmptyLibraryException e) {
            modelAndView.addObject(MESSAGE, "Question Library is empty!!Please add Questions!!");
            modelAndView.setViewName("questionLibraryPortal");
        }
        return modelAndView;
    }

    @PostMapping("addQuestion")
    public ModelAndView addQuestion(QuestionDto questionDto, @RequestParam("value") List<String> optionsValue,
                                    @RequestParam("isAnswer") List<Boolean> isAnswer) {
        Set<OptionDto> options = new HashSet<>();
        int i = 0;
        while (i < 4 && !optionsValue.get(i).isEmpty()) {
            OptionDto option = new OptionDto();
            option.setValue(optionsValue.get(i));
            option.setAnswer(isAnswer.get(i));
            options.add(option);
            i++;
        }
        questionService.addQuestion(questionDto, options);
        ModelAndView modelAndView = viewQuestions();
        modelAndView.addObject(MESSAGE, "Question Added Succesfully!!!");
        return modelAndView;
    }

    @RequestMapping("editQuestion")
    public ModelAndView editQuestion(Integer questionId)  {
        ModelAndView modelAndView = new ModelAndView();
        QuestionDto questionDto = null;
        try {
            questionDto = questionService.getQuestionByID(questionId);
        } catch (InvalidIDException e) {
            modelAndView.addObject(MESSAGE, "Wrong Question ID");
        }
        modelAndView.addObject("question", questionDto);
        modelAndView.setViewName("editQuestion");
        return modelAndView;
    }


    @PostMapping("updateQuestion")
    public ModelAndView updateQuestion(QuestionDto questionDto, @RequestParam("value") List<String> optionsValue,
                                       @RequestParam("isAnswer") List<Boolean> isAnswer) {
        Set<OptionDto> optionsDtos = new HashSet<>();
        int i = 0;
        while (i < optionsValue.size() && !optionsValue.get(i).isEmpty()) {
            OptionDto optionDto = new OptionDto();
            optionDto.setValue(optionsValue.get(i));
            optionDto.setAnswer(isAnswer.get(i));
            optionsDtos.add(optionDto);
            i++;
        }
        questionService.modifyQuestion(questionDto, optionsDtos);
        ModelAndView modelAndView = viewQuestions();
        modelAndView.addObject(MESSAGE, "Question Modified Succesfully!!!");
        return modelAndView;
    }


    @RequestMapping("deleteQuestion")
    public ModelAndView deleteQuestion(int questionId) {
        ModelAndView modelAndView;
        try {
            questionService.removeQuestion(questionId);
            modelAndView = viewQuestions();
            modelAndView.addObject(MESSAGE, "Question Deleted!!!");
        } catch (DataIntegrityViolationException e) {
            modelAndView = viewQuestions();
            modelAndView.addObject(MESSAGE, "Question cannot be deleted as it is used in Quiz!!!");
        }
        return modelAndView;
    }

}
