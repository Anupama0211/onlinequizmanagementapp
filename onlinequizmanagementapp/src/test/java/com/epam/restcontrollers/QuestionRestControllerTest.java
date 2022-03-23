package com.epam.restcontrollers;

import com.epam.dto.QuestionDto;
import com.epam.entities.Option;
import com.epam.services.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    }

    @Test
    void addQuestion() throws Exception {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setTitle("What is JAVA?");
        questionDto.setMarks(1);
        Option option = new Option();
        option.setAnswer(true);
        option.setValue("OOP");
        questionDto.setOptions(List.of(option));

        when(questionService.addQuestion(questionDto)).thenReturn(questionDto);
        mockMvc.perform(post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(questionDto)))
                .andExpect(status().isCreated());

    }

    @Test
    void updateQuestion() throws Exception {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(2);
        questionDto.setTitle("JPA is JAVA Programming API.");
        questionDto.setMarks(1);
        questionDto.setTopic("Programming");
        Option option = new Option();
        option.setAnswer(true);
        option.setValue("OOP");
        questionDto.setOptions(List.of(option));

        when(questionService.modifyQuestion(questionDto)).thenReturn(questionDto);
        mockMvc.perform(put("/questions/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(questionDto)))
                .andExpect(status().isOk());
             //   .andExpect(jsonPath("$.title").value(questionDto.getTitle()));
    }

    @Test
    void deleteQuestion() throws Exception {
        mockMvc.perform(delete("/questions/1"))
                .andExpect(status().isNoContent());

    }
}