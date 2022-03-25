package com.epam.restcontrollers;

import com.epam.dto.QuestionDto;
import com.epam.entities.Option;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;


import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = QuestionRestController.class)
class QuestionRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuestionService questionService;

    static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void viewQuestions() throws Exception {

        when(questionService.getAllQuestions()).thenReturn(List.of(new QuestionDto()));
        mockMvc.perform(get("/questions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        when(questionService.getAllQuestions()).thenThrow(EmptyLibraryException.class);
        mockMvc.perform(get("/questions"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addQuestion() throws Exception {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(2);
        questionDto.setTitle("JPA is JAVA Programming API.");
        questionDto.setMarks(1);
        questionDto.setDifficulty("Hard");
        questionDto.setTopic("Programming");
        Option option1 = new Option();
        option1.setAnswer(true);
        option1.setValue("OOP");
        Option option2 = new Option();
        option2.setAnswer(true);
        option2.setValue("OOP");
        questionDto.setOptions(List.of(option1, option2));

        when(questionService.addQuestion(questionDto)).thenReturn(questionDto);
        mockMvc.perform(post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(questionDto)))
                .andExpect(status().isCreated());
        verify(questionService).addQuestion(any(QuestionDto.class));

    }

    @Test
    void addInvalidQuestion() throws Exception {
        QuestionDto questionDto = new QuestionDto();

        when(questionService.addQuestion(questionDto)).thenReturn(questionDto);
        mockMvc.perform(post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(questionDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateQuestion() throws Exception {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(2);
        questionDto.setTitle("JPA is JAVA Programming API.");
        questionDto.setMarks(1);
        questionDto.setDifficulty("Hard");
        questionDto.setTopic("Programming");
        Option option1 = new Option();
        option1.setAnswer(true);
        option1.setValue("OOP");
        Option option2 = new Option();
        option2.setAnswer(true);
        option2.setValue("OOP");
        questionDto.setOptions(List.of(option1, option2));

        when(questionService.modifyQuestion(questionDto)).thenReturn(questionDto);
        MvcResult mvcResult = mockMvc.perform(put("/questions")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(questionDto)))
                .andExpect(status().isOk())
                .andDo(print())
                //.andExpect(jsonPath("$.title").value(questionDto.getTitle()))
                .andReturn();
        //System.out.println(mvcResult.getResponse().getContentAsString());
        //  assertThat(mvcResult.getResponse().getContentAsString()).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(questionDto));

        // assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(questionDto);
    }

    @Test
    void updateQuestionWithInavlidId() throws Exception {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(2);
        questionDto.setTitle("JPA is JAVA Programming API.");
        questionDto.setMarks(1);
        questionDto.setDifficulty("Hard");
        questionDto.setTopic("Programming");
        Option option1 = new Option();
        option1.setAnswer(true);
        option1.setValue("OOP");
        Option option2 = new Option();
        option2.setAnswer(true);
        option2.setValue("OOP");
        questionDto.setOptions(List.of(option1, option2));

        when(questionService.modifyQuestion(any(QuestionDto.class))).thenThrow(InvalidIDException.class);
        mockMvc.perform(put("/questions")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(questionDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteQuestionWhenInQuiz() throws Exception {
        doThrow(DataIntegrityViolationException.class).when(questionService).removeQuestion(1);
        mockMvc.perform(delete("/questions/1"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void deleteQuestion() throws Exception {
        mockMvc.perform(delete("/questions/1"))
                .andExpect(status().isNoContent());
        verify(questionService).removeQuestion(1);

        doThrow(EmptyResultDataAccessException.class).when(questionService).removeQuestion(1);
        mockMvc.perform(delete("/questions/1"))
                .andExpect(status().isNotFound());
    }
}