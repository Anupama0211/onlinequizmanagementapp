package com.epam.restcontrollers;

import com.epam.dto.OptionDto;
import com.epam.dto.QuestionDto;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuestionService;
import com.epam.util.JwtUtil;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;



import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = QuestionRestController.class)
class QuestionRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuestionService questionService;
    @MockBean
    UserDetailsService userDetailsService;
    @MockBean
    JwtUtil jwtUtil;

    static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser(username = "qwerty",roles = {"USER","ADMIN"})
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
    @WithMockUser(username = "qwerty",roles = {"USER","ADMIN"})
    void addQuestion() throws Exception {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(2);
        questionDto.setTitle("JPA is JAVA Programming API.");
        questionDto.setMarks(1);
        questionDto.setDifficulty("Hard");
        questionDto.setTopic("Programming");
        OptionDto option1 = new OptionDto();
        option1.setAnswer(true);
        option1.setValue("OOP");
        OptionDto option2 = new OptionDto();
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
    @WithMockUser(username = "qwerty",roles = {"USER","ADMIN"})
    void addInvalidQuestion() throws Exception {
        QuestionDto questionDto = new QuestionDto();

        when(questionService.addQuestion(questionDto)).thenReturn(questionDto);
        mockMvc.perform(post("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(questionDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "qwerty",roles = {"USER","ADMIN"})
    void updateQuestion() throws Exception {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(2);
        questionDto.setTitle("JPA is JAVA Programming API.");
        questionDto.setMarks(1);
        questionDto.setDifficulty("Hard");
        questionDto.setTopic("Programming");
        OptionDto option1 = new OptionDto();
        option1.setAnswer(true);
        option1.setValue("OOP");
        OptionDto option2 = new OptionDto();
        option2.setAnswer(true);
        option2.setValue("OOP");
        questionDto.setOptions(List.of(option1, option2));

        when(questionService.modifyQuestion(questionDto,2)).thenReturn(questionDto);
        mockMvc.perform(put("/questions/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(questionDto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "qwerty",roles = {"USER","ADMIN"})
    void updateQuestionWithInavlidId() throws Exception {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(2);
        questionDto.setTitle("JPA is JAVA Programming API.");
        questionDto.setMarks(1);
        questionDto.setDifficulty("Hard");
        questionDto.setTopic("Programming");
        OptionDto option1 = new OptionDto();
        option1.setAnswer(true);
        option1.setValue("OOP");
        OptionDto option2 = new OptionDto();
        option2.setAnswer(true);
        option2.setValue("OOP");
        questionDto.setOptions(List.of(option1, option2));

        when(questionService.modifyQuestion(any(QuestionDto.class),anyInt())).thenThrow(InvalidIDException.class);
        mockMvc.perform(put("/questions/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(questionDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "qwerty",roles = {"USER","ADMIN"})
    void deleteQuestionWhenInQuiz() throws Exception {
        doThrow(DataIntegrityViolationException.class).when(questionService).removeQuestion(1);
        mockMvc.perform(delete("/questions/1"))
                .andExpect(status().isBadRequest());
    }
    @Test
    @WithMockUser(username = "qwerty",roles = {"USER","ADMIN"})
    void deleteQuestion() throws Exception {
        mockMvc.perform(delete("/questions/1"))
                .andExpect(status().isNoContent());
        verify(questionService).removeQuestion(1);

        doThrow(EmptyResultDataAccessException.class).when(questionService).removeQuestion(1);
        mockMvc.perform(delete("/questions/1"))
                .andExpect(status().isNotFound());
    }
}