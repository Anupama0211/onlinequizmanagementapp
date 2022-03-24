package com.epam.restcontrollers;

import com.epam.dto.ExceptionResponse;
import com.epam.exceptions.EmptyLibraryException;
import com.epam.exceptions.InvalidIDException;
import com.epam.exceptions.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;


@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(InvalidIDException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidIDException(InvalidIDException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError(exception.getMessage());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        exceptionResponse.setTimestamp(new Date().toString());
        exceptionResponse.setPath(request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError("No such Id exists!");
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        exceptionResponse.setTimestamp(new Date().toString());
        exceptionResponse.setPath(request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyLibraryException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyLibraryException(EmptyLibraryException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError(exception.getMessage());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        exceptionResponse.setTimestamp(new Date().toString());
        exceptionResponse.setPath(request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError(exception.getMessage());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        exceptionResponse.setTimestamp(new Date().toString());
        exceptionResponse.setPath(request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError("Question is present in the quiz!Cannot be deleted");
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        exceptionResponse.setTimestamp(new Date().toString());
        exceptionResponse.setPath(request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest webRequest) {
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError(errors.toString());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        exceptionResponse.setTimestamp(new Date().toString());
        exceptionResponse.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
