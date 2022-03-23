package com.epam.restcontrollers;


import com.epam.dto.QuizDto;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/quizzes")
public class QuizRestController {

    @Autowired
    QuizService quizService;

    @GetMapping
    public ResponseEntity<List<QuizDto>> viewQuizzes() throws EmptyLibraryException {
        return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
    }

    @DeleteMapping("{quizId}")
    public ResponseEntity<HttpStatus> deleteAQuiz(@PathVariable int quizId) {
        quizService.deleteQuiz(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("quiz/{quizId}")
    public ResponseEntity<QuizDto> viewAQuiz(@PathVariable int quizId) throws InvalidIDException {
        return new ResponseEntity<>(quizService.getAQuiz(quizId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<QuizDto> insertQuiz(@RequestParam("questionIds") List<Integer> questionIds,
                                              @RequestBody QuizDto quizDto) throws InvalidIDException {
        return new ResponseEntity<>(quizService.insertQuiz(quizDto, questionIds), HttpStatus.CREATED);
    }

    @PutMapping("quiz")
    public ResponseEntity<QuizDto> updateQuiz(@RequestParam("questionIds") List<Integer> questionIds,
                                              @RequestBody QuizDto quizDto) throws InvalidIDException {
        return new ResponseEntity<>(quizService.insertQuiz(quizDto, questionIds), HttpStatus.OK);
    }


    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteQuestionInQuiz(@RequestParam int questionId, @RequestParam int quizId) throws InvalidIDException {
        quizService.deleteQuestionInQuiz(quizId, questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

