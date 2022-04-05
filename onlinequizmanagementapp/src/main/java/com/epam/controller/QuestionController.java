package com.epam.controller;

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

import javax.validation.Valid;
import java.util.List;

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
    public ModelAndView addQuestion(@Valid QuestionDto questionDto) {
        questionService.addQuestion(questionDto);
        ModelAndView modelAndView = viewQuestions();
        modelAndView.addObject(MESSAGE, "Question Added Succesfully!!!");
        return modelAndView;
    }

    @RequestMapping("editQuestion")
    public ModelAndView editQuestion(@RequestParam int questionId) throws InvalidIDException {
        ModelAndView modelAndView = new ModelAndView();
        QuestionDto questionDto;
        questionDto = questionService.getQuestionByID(questionId);
        modelAndView.addObject("question", questionDto);
        modelAndView.setViewName("editQuestion");
        return modelAndView;
    }

    @PostMapping("updateQuestion")
    public ModelAndView updateQuestion(@RequestParam int questionId, @Valid QuestionDto questionDto) throws InvalidIDException {
        questionService.modifyQuestion(questionDto, questionId);
        ModelAndView modelAndView = viewQuestions();
        modelAndView.addObject(MESSAGE, "Question Modified Succesfully!!!");
        return modelAndView;
    }


    @RequestMapping("deleteQuestion")
    public ModelAndView deleteQuestion(@RequestParam int questionId) {
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
