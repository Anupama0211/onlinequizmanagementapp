package com.epam.controller;


import com.epam.dto.OptionDto;
import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.entities.Option;
import com.epam.entities.Question;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.services.QuestionService;
import com.epam.services.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = QuizController.class)
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuizService quizService;
    @MockBean
    private QuestionService questionService;
    QuizDto quizDto;
    QuestionDto questionDto;

    @BeforeEach
    void setUp() {
        questionDto = new QuestionDto();
        questionDto.setQuestionId(1);
        questionDto.setDifficulty("Easy");
        questionDto.setTitle("What is JAVA");
        questionDto.setTopic("Programming");
        questionDto.setMarks(2);
        questionDto.setOptions(List.of(new OptionDto(1, "Island", false)
                , new OptionDto(1, "Coffee", true)));

        quizDto = new QuizDto();
        quizDto.setTitle("ABCDEFGH");
        quizDto.setQuizId(111);
        Set<QuestionDto> questions = new HashSet<>();
        questions.add(questionDto);
        quizDto.setQuestions(Set.of(new QuestionDto()));
        quizDto.setTitle("DEMO");
    }

    @Test
    void quizLibraryPortal() throws Exception {

        mockMvc.perform(get("/quizLibraryPortal"))
                .andExpect(status().isOk())
                .andExpect(view().name("quizLibraryPortal"));
    }

    @Test
    void viewQuizTitlesWhenQuizNotEmpty() throws Exception {
        List<QuizDto> quizzes = List.of(quizDto);
        when(quizService.getAllQuizzes()).thenReturn(quizzes);
        mockMvc.perform(get("/viewQuizTitles"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewQuizTitles"))
                .andExpect(model().attribute("quizzes", quizzes));
    }

    @Test
    void viewQuizTitlesWhenQuizEmpty() throws Exception {
        when(quizService.getAllQuizzes()).thenThrow(EmptyLibraryException.class);
        mockMvc.perform(get("/viewQuizTitles"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewQuizTitles"))
                .andExpect(model().attribute("message", "Quiz Library is empty!!Please create Quiz!!"));
    }

    @Test
    void deleteAQuiz() throws Exception {

        mockMvc.perform(get("/deleteAQuiz?quizId=111"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewQuizTitles"))
                .andExpect(model().attribute("message", "Quiz Deleted!!!"));
        verify(quizService).deleteQuiz(111);
    }

    @Test
    void viewAQuizWhenEmpty() throws Exception {
        quizDto.setQuestions(Set.of());
        when(quizService.getAQuiz(111)).thenReturn(quizDto);
        mockMvc.perform(get("/viewAQuiz?quizId=111"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewAQuiz"))
                .andExpect(model().attribute("message", "Quiz is Empty"))
                .andExpect(model().attribute("id", 111))
                .andExpect(model().attribute("title", quizDto.getTitle()))
                .andExpect(model().attribute("questions", quizDto.getQuestions()));

    }

    @Test
    void viewAQuizWhenNotEmpty() throws Exception {
        when(quizService.getAQuiz(111)).thenReturn(quizDto);
        mockMvc.perform(get("/viewAQuiz?quizId=111"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewAQuiz"))
                .andExpect(model().attribute("id", 111))
                .andExpect(model().attribute("title", quizDto.getTitle()))
                .andExpect(model().attribute("questions", quizDto.getQuestions()));

    }

    @Test
    void createQuiz() throws Exception {
        List<QuestionDto> questions = List.of(questionDto);
        when(questionService.getAllQuestions()).thenReturn(questions);
        mockMvc.perform(get("/createQuiz"))
                .andExpect(status().isOk())
                .andExpect(view().name("createQuiz"))
                .andExpect(model().attribute("questions", questions));
    }

    @Test
    void createQuizWhenQUestionIsEmpty() throws Exception {
        when(questionService.getAllQuestions()).thenThrow(EmptyLibraryException.class);
        mockMvc.perform(get("/createQuiz"))
                .andExpect(status().isOk())
                .andExpect(view().name("createQuiz"))
                .andExpect(model().attribute("message", "Question Library is empty!!Add Questions in the Question Library!"));
    }

    @Test
    void insertQuiz() throws Exception {
        when(quizService.insertQuiz(any(QuizDto.class), anyList())).thenReturn(quizDto);
        when(quizService.getAQuiz(111)).thenReturn(quizDto);
        mockMvc.perform(post("/insertQuiz")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("questionIds", "1","2","3","4")
                        .sessionAttr("quizDto", quizDto))
                .andExpect(status().isOk())
                .andExpect(view().name("viewAQuiz"));
    }
    @Test
    void updateQuiz() throws Exception {
        when(quizService.updateQuiz(any(QuizDto.class), anyList(),anyInt())).thenReturn(quizDto);
        when(quizService.getAQuiz(111)).thenReturn(quizDto);
        mockMvc.perform(post("/updateQuiz?quizId=111")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("questionIds", "1", "2", "3", "4")
                        .sessionAttr("quizDto", quizDto))
                .andExpect(status().isOk())
                .andExpect(view().name("viewAQuiz"));
    }


    @Test
    void selectQuestionForQuizWhenQuestionLibraryNotEmpty() throws Exception {
        when(questionService.getAllQuestions()).thenReturn(List.of(questionDto));
        when(quizService.getAQuiz(111)).thenReturn(quizDto);
        mockMvc.perform(get("/selectQuestionForQuiz?quizId=111"))
                .andExpect(status().isOk())
                .andExpect(view().name("selectQuestionForQuiz"))
                .andExpect(model().attribute("quiz", quizDto))
                .andExpect(model().attribute("questions", List.of(questionDto)));
    }

    @Test
    void selectQuestionForQuizWhenQuestionLibraryIsEmpty() throws Exception {
        when(questionService.getAllQuestions()).thenThrow(EmptyLibraryException.class);
        mockMvc.perform(get("/selectQuestionForQuiz?quizId=111"))
                .andExpect(status().isOk())
                .andExpect(view().name("selectQuestionForQuiz"))
                .andExpect(model().attribute("message", "Question Library is empty"));
    }

    @Test
    void deleteQuestionInQuiz() throws Exception {
        when(quizService.getAQuiz(111)).thenReturn(quizDto);
        mockMvc.perform(get("/deleteQuestionInQuiz?questionId=1&quizId=111"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewAQuiz"))
                .andExpect(model().attribute("message", "Question Deleted"));
    }
}
