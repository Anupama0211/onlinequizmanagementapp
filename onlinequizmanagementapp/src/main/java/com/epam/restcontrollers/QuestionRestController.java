package com.epam.restcontrollers;

import com.epam.dto.QuestionDto;
import com.epam.services.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionRestController {

    @Autowired
    QuestionService questionService;


    @Operation(description = "It Fetches all the questions")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "200", description = "Sucessfull")

    @GetMapping
    public ResponseEntity<List<QuestionDto>> viewQuestions() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @Operation(description = "It adds the question")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping
    public ResponseEntity<QuestionDto> addQuestion(@RequestBody @Valid QuestionDto questionDto) {
        return new ResponseEntity<>(questionService.addQuestion(questionDto), HttpStatus.CREATED);
    }

    @Operation(description = "It updates the question")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "200", description = "Sucessfull")
    @PutMapping("{questionId}")
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody @Valid QuestionDto questionDto,@PathVariable int questionId) {
        return new ResponseEntity<>(questionService.modifyQuestion(questionDto,questionId), HttpStatus.OK);

    }

    @Operation(description = "It deletes the question")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "204", description = "Deleted")
    @DeleteMapping("{questionId}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable int questionId) {
        questionService.removeQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
