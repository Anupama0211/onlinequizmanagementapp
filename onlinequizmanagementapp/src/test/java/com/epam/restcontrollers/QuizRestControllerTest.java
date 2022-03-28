package com.epam.restcontrollers;

import com.epam.dto.QuestionDto;
import com.epam.dto.QuizDto;
import com.epam.entities.Quiz;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuestionService;
import com.epam.services.QuizService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = QuizRestController.class)
class QuizRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    QuizService quizService;


    static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void viewQuizzes() throws Exception {
        when(quizService.getAllQuizzes()).thenReturn(List.of(new QuizDto()));
        mockMvc.perform(get("/quizzes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        when(quizService.getAllQuizzes()).thenThrow(EmptyLibraryException.class);
        mockMvc.perform(get("/quizzes"))
                .andExpect(status().isNotFound());

    }

    @Test
    void deleteAQuiz() throws Exception {
        mockMvc.perform(delete("/quizzes/1"))
                .andExpect(status().isNoContent());

        doThrow(EmptyResultDataAccessException.class).when(quizService).deleteQuiz(1);
        mockMvc.perform(delete("/quizzes/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void viewAQuiz() throws Exception {
        when(quizService.getAQuiz(1)).thenReturn(new QuizDto());
        mockMvc.perform(get("/quizzes/1"))
                .andExpect(status().isOk());

        when(quizService.getAQuiz(1)).thenThrow(InvalidIDException.class);
        mockMvc.perform(get("/quizzes/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void insertQuiz() throws Exception {
        QuizDto quizDto = new QuizDto();
        quizDto.setTitle("QuizTest");
        when(quizService.insertQuiz(quizDto, List.of(1, 2, 3))).thenReturn(quizDto);
        mockMvc.perform(post("/quizzes?questionIds=1,2,3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(quizDto)))
                .andExpect(status().isCreated());

        quizDto.setTitle("");
        mockMvc.perform(post("/quizzes?questionIds=1,2,3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(quizDto)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void insertQuizWithInavlidQuestionId() throws Exception {
        QuizDto quizDto = new QuizDto();
        quizDto.setTitle("QuizTest");
        when(quizService.insertQuiz(any(QuizDto.class), anyList())).thenThrow(InvalidIDException.class);
        mockMvc.perform(post("/quizzes?questionIds=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(quizDto)))
                .andExpect(status().isNotFound());
        ArgumentCaptor<List<Integer>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(quizService).insertQuiz(any(QuizDto.class), argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(List.of(1));
    }

    @Test
    void insertQuizWithMissingRequestParam() throws Exception {
        QuizDto quizDto = new QuizDto();
        quizDto.setTitle("QuizTest");
        mockMvc.perform(post("/quizzes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(quizDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateQuiz() throws Exception {
        QuizDto quizDto = new QuizDto();
        quizDto.setTitle("Quiz Test");
        when(quizService.insertQuiz(quizDto, List.of(1, 2, 3))).thenReturn(quizDto);
        mockMvc.perform(put("/quizzes/1?questionIds=1,2,3").
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(quizDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteQuestionInQuiz() throws Exception {
        mockMvc.perform(delete("/quizzes/1/1"))
                .andExpect(status().isNoContent());

        doThrow(InvalidIDException.class).when(quizService).deleteQuestionInQuiz(1, 1);
        mockMvc.perform(delete("/quizzes/1/1"))
                .andExpect(status().isNotFound());
    }
}