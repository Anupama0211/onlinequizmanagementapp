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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
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
                .andExpect(status().isNoContent());

    }

    @Test
    void deleteAQuiz() throws Exception {
        mockMvc.perform(delete("/quizzes/1"))
                .andExpect(status().isNoContent());
        doThrow(EmptyResultDataAccessException.class).when(quizService).deleteQuiz(1);
        mockMvc.perform(delete("/quizzes/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void viewAQuiz() throws Exception {
        when(quizService.getAQuiz(1)).thenReturn(new QuizDto());
        mockMvc.perform(get("/quizzes/quiz/1"))
                .andExpect(status().isOk());

        when(quizService.getAQuiz(1)).thenThrow(InvalidIDException.class);
        mockMvc.perform(get("/quizzes/quiz/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void insertQuiz() throws Exception {
        QuizDto quizDto = new QuizDto();
        when(quizService.insertQuiz(quizDto, List.of(1, 2, 3))).thenReturn(quizDto);
        mockMvc.perform(post("/quizzes?questionIds=1,2,3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(quizDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateQuiz() throws Exception {
        QuizDto quizDto = new QuizDto();
        when(quizService.insertQuiz(quizDto, List.of(1, 2, 3))).thenReturn(quizDto);
        mockMvc.perform(put("/quizzes/quiz?questionIds=1,2,3").
                        contentType(MediaType.APPLICATION_JSON).
                        content(objectMapper.writeValueAsString(quizDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteQuestionInQuiz() throws Exception {
        mockMvc.perform(delete("/quizzes?questionId=1&quizId=1"))
                .andExpect(status().isNoContent());
    }
}