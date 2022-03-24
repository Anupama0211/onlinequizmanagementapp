package com.epam.restcontrollers;


import com.epam.dto.QuizDto;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<List<QuizDto>> viewQuizzes() throws EmptyLibraryException {
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
    @GetMapping("quiz/{quizId}")
    public ResponseEntity<QuizDto> viewAQuiz(@PathVariable int quizId) throws InvalidIDException {
        return new ResponseEntity<>(quizService.getAQuiz(quizId), HttpStatus.OK);
    }

    @Operation(description = "It inserts a quiz")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PostMapping
    public ResponseEntity<QuizDto> insertQuiz(@RequestParam("questionIds") List<Integer> questionIds,
                                              @Valid @RequestBody QuizDto quizDto) throws InvalidIDException {
        return new ResponseEntity<>(quizService.insertQuiz(quizDto, questionIds), HttpStatus.CREATED);
    }

    @Operation(description = "It updates the quiz")
    @ApiResponse(responseCode = "200", description = "Successful")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PutMapping("quiz")
    public ResponseEntity<QuizDto> updateQuiz(@RequestParam("questionIds") List<Integer> questionIds,
                                              @Valid @RequestBody QuizDto quizDto) throws InvalidIDException {
        return new ResponseEntity<>(quizService.insertQuiz(quizDto, questionIds), HttpStatus.OK);
    }

    @Operation(description = "It deletes a question in a quiz")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteQuestionInQuiz(@RequestParam int questionId, @RequestParam int quizId) throws InvalidIDException {
        quizService.deleteQuestionInQuiz(quizId, questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

