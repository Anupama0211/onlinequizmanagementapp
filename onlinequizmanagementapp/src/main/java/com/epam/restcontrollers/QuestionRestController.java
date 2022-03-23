package com.epam.restcontrollers;

import com.epam.dto.QuestionDto;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionRestController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionDto>> viewQuestions() throws EmptyLibraryException {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<QuestionDto> addQuestion(@RequestBody QuestionDto questionDto) {
        return new ResponseEntity<>(questionService.addQuestion(questionDto), HttpStatus.CREATED);
    }

    @PutMapping(value="question",produces = "application/json")
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody QuestionDto questionDto) throws InvalidIDException {
        System.out.println(questionDto);
        return new ResponseEntity<>(questionService.modifyQuestion(questionDto), HttpStatus.OK);

    }

    @DeleteMapping("{questionId}")
    public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable int questionId) {
        questionService.removeQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
