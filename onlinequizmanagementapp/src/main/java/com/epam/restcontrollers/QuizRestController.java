package com.epam.restcontrollers;


import com.epam.dto.QuizDto;
import com.epam.services.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/quizzes")
public class QuizRestController {

    @Autowired
    QuizService quizService;

    @Operation(description = "It Fetches all the quizzes")
    @ApiResponse(responseCode = "204", description = "Bad Request")
    @ApiResponse(responseCode = "200", description = "Sucessfull")
    @GetMapping
    public ResponseEntity<List<QuizDto>> viewQuizzes() {
        return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
    }

    @Operation(description = "It deletes the quiz")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @DeleteMapping("{quizId}")
    public ResponseEntity<HttpStatus> deleteAQuiz(@PathVariable int quizId) {
        quizService.deleteQuiz(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(description = "It fetches a quiz")
    @ApiResponse(responseCode = "200", description = "Successful")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @GetMapping("{quizId}")
    public ResponseEntity<QuizDto> viewAQuiz(@PathVariable int quizId) {
        return new ResponseEntity<>(quizService.getAQuiz(quizId), HttpStatus.OK);
    }

    @Operation(description = "It inserts a quiz")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PostMapping
    public ResponseEntity<QuizDto> insertQuiz(@RequestParam("questionIds") List<Integer> questionIds,
                                              @Valid @RequestBody QuizDto quizDto) {
        return new ResponseEntity<>(quizService.insertQuiz(quizDto, questionIds), HttpStatus.CREATED);
    }

    @Operation(description = "It updates the quiz")
    @ApiResponse(responseCode = "200", description = "Successful")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PutMapping("{quizId}")
    public ResponseEntity<QuizDto> updateQuiz(@RequestParam("questionIds") List<Integer> questionIds,
                                              @Valid @RequestBody QuizDto quizDto, @PathVariable int quizId) {
        return new ResponseEntity<>(quizService.updateQuiz(quizDto, questionIds, quizId), HttpStatus.OK);
    }

    @Operation(description = "It deletes a question in a quiz")
    @ApiResponse(responseCode = "204", description = "No Content")
    @DeleteMapping("{quizId}/{questionId}")
    public ResponseEntity<HttpStatus> deleteQuestionInQuiz(@PathVariable int quizId, @PathVariable int questionId) {
        quizService.deleteQuestionInQuiz(quizId, questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

