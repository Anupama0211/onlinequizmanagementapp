package com.epam.controller;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.*;


import com.epam.dto.OptionDto;
import com.epam.dto.QuestionDto;
import com.epam.entities.Option;
import com.epam.entities.Question;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.services.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = QuestionController.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @Test
    void questionLibraryPortal() throws Exception {
        mockMvc.perform(get("/questionLibraryPortal"))
                .andExpect(status().isOk())
                .andExpect(view().name("questionLibraryPortal"));
    }

    @Test
    void createQuestion() throws Exception {
        mockMvc.perform(get("/createQuestion"))
                .andExpect(status().isOk())
                .andExpect(view().name("createQuestion"));
    }

    @Test
    void viewQuestion() throws Exception {
        Option option = new Option();
        Set<Option> options = Set.of(option);
        QuestionDto question1 = new QuestionDto();
        QuestionDto question2 = new QuestionDto();
        QuestionDto question3 = new QuestionDto();
        question1.setOptions(options);
        question2.setOptions(options);
        question3.setOptions(options);
        List<QuestionDto> questions = Arrays.asList(question1, question2, question3);


        when(questionService.getAllQuestions()).thenReturn(questions);
        mockMvc.perform(get("/viewQuestions"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewQuestions"))
                .andExpect(model().attribute("questions", hasSize(3)));

        when(questionService.getAllQuestions()).thenThrow(EmptyLibraryException.class);
        mockMvc.perform(get("/viewQuestions"))
                .andExpect(status().isOk())
                .andExpect(view().name("questionLibraryPortal"))
                .andExpect(model().attribute("message", "Question Library is empty!!Please add Questions!!"));
    }

    @Test
    void addQuestion() throws Exception {

        mockMvc.perform(post("/addQuestion")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("value", "1", "2", "3", "4")
                        .param("isAnswer", "true", "false", "false", "false"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewQuestions"))
                .andExpect(model().attribute("message", "Question Added Succesfully!!!"));
        verify(questionService).addQuestion(any(QuestionDto.class), anySet());
    }

    @Test
    void editQuestion() throws Exception {
        mockMvc.perform(get("/editQuestion?questionId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("editQuestion"));


    }

    @Test
    void updateQuestion() throws Exception {
        Question question1 = new Question();
        Option option1 = new Option();
        option1.setValue("1");
        Option option2 = new Option();
        option2.setValue("2");
        Option option3 = new Option();
        option3.setValue("3");
        Option option4 = new Option();
        option4.setValue("4");
        Set<Option> options = new HashSet<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        question1.setOptions(options);
        mockMvc.perform(post("/updateQuestion")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("value", "1", "2", "3", "4")
                        .param("isAnswer", "true", "false", "false", "false"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewQuestions"))
                .andExpect(model().attribute("message", "Question Modified Succesfully!!!"));
        verify(questionService).modifyQuestion(any(QuestionDto.class), anySet());
    }


    @Test
    void deleteQuestion() throws Exception {
        Question question = new Question();
        mockMvc.perform(get("/deleteQuestion?questionId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewQuestions"))
                .andExpect(model().attribute("message", "Question Deleted!!!"));

        verify(questionService).removeQuestion(1);
        doThrow(DataIntegrityViolationException.class).when(questionService).removeQuestion(1);
        mockMvc.perform(get("/deleteQuestion?questionId=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewQuestions"))
                .andExpect(model().attribute("message", "Question cannot be deleted as it is used in Quiz!!!"));

    }
}