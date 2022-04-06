package com.epam.controller;


import com.epam.dto.QuizDto;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuestionService;
import com.epam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import java.util.List;


@Controller
public class QuizController {
    @Autowired
    QuizService quizService;
    @Autowired
    QuestionService questionService;
    private static final String MESSAGE = "message";
    private static final String QUESTIONS = "questions";

    @RequestMapping("quizLibraryPortal")
    public String quizLibraryPortal() {
        return "quizLibraryPortal";
    }

    @RequestMapping("viewQuizTitles")
    public ModelAndView viewQuizTitles() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<QuizDto> quizDtoes = quizService.getAllQuizzes();
            modelAndView.addObject("quizzes", quizDtoes);
        } catch (EmptyLibraryException e) {
            modelAndView.addObject(MESSAGE, "Quiz Library is empty!!Please create Quiz!!");
        }
        modelAndView.setViewName("viewQuizTitles");
        return modelAndView;
    }

    @RequestMapping("deleteAQuiz")
    public ModelAndView deleteAQuiz(@RequestParam int quizId) {
        quizService.deleteQuiz(quizId);
        ModelAndView modelAndView = viewQuizTitles();
        modelAndView.addObject(MESSAGE, "Quiz Deleted!!!");
        return modelAndView;
    }

    @RequestMapping("viewAQuiz")
    public ModelAndView viewAQuiz(@RequestParam int quizId) throws InvalidIDException {
        ModelAndView modelAndView = new ModelAndView();
        QuizDto quizDto = quizService.getAQuiz(quizId);
        if (quizDto.getQuestions().isEmpty()) {
            modelAndView.addObject(MESSAGE, "Quiz is Empty");
        }
        modelAndView.addObject("id", quizDto.getQuizId());
        modelAndView.addObject("title", quizDto.getTitle());
        modelAndView.addObject(QUESTIONS, quizDto.getQuestions());
        modelAndView.setViewName("viewAQuiz");
        return modelAndView;
    }

    @RequestMapping("createQuiz")
    public ModelAndView createQuiz() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject(QUESTIONS, questionService.getAllQuestions());
        } catch (EmptyLibraryException e) {
            modelAndView.addObject(MESSAGE, "Question Library is empty!!Add Questions in the Question Library!");
        }
        modelAndView.setViewName("createQuiz");
        return modelAndView;
    }

    @PostMapping("insertQuiz")
    public ModelAndView insertQuiz(@RequestParam("questionIds") List<Integer> questionIds,  QuizDto quizDto)  {
        quizDto = quizService.insertQuiz(quizDto, questionIds);
        ModelAndView modelAndView = viewAQuiz(quizDto.getQuizId());
        modelAndView.addObject(MESSAGE, "Quiz Created!!!");
        return modelAndView;
    }

    @PostMapping("updateQuiz")
    public ModelAndView updateQuiz(@RequestParam int quizId, @RequestParam("questionIds") List<Integer> questionIds,QuizDto quizDto) throws InvalidIDException {
        quizDto = quizService.updateQuiz(quizDto, questionIds, quizId);
        ModelAndView modelAndView = viewAQuiz(quizDto.getQuizId());
        modelAndView.addObject(MESSAGE, "Quiz Updated!!!");
        return modelAndView;
    }

    @RequestMapping("selectQuestionForQuiz")
    public ModelAndView selectQuestionForQuiz(int quizId) throws InvalidIDException {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject(QUESTIONS, questionService.getAllQuestions());
            modelAndView.addObject("quiz", quizService.getAQuiz(quizId));
        } catch (EmptyLibraryException e) {
            modelAndView.addObject(MESSAGE, "Question Library is empty");
        }
        modelAndView.setViewName("selectQuestionForQuiz");
        return modelAndView;
    }

    @RequestMapping("deleteQuestionInQuiz")
    public ModelAndView deleteQuestionInQuiz(@RequestParam int questionId, @RequestParam int quizId) throws InvalidIDException {
        quizService.deleteQuestionInQuiz(quizId, questionId);
        ModelAndView modelAndView = viewAQuiz(quizId);
        modelAndView.addObject(MESSAGE, "Question Deleted");
        return modelAndView;
    }

}
