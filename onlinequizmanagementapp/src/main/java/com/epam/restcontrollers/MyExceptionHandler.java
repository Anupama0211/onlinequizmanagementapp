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
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;


@RestControllerAdvice
public class MyExceptionHandler {


    ExceptionResponse getExceptionResponse(String error, HttpStatus badRequest, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError(error);
        exceptionResponse.setStatus(badRequest.name());
        exceptionResponse.setTimestamp(new Date().toString());
        exceptionResponse.setPath(request.getDescription(false));
        return exceptionResponse;
    }

    @ExceptionHandler(InvalidIDException.class)
    ResponseEntity<ExceptionResponse> handleInvalidIDException(InvalidIDException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    ResponseEntity<ExceptionResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse("No such Id exists!", HttpStatus.NOT_FOUND, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyLibraryException.class)
    ResponseEntity<ExceptionResponse> handleEmptyLibraryException(EmptyLibraryException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse("Question is present in the quiz!Cannot be deleted", HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    ResponseEntity<ExceptionResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest webRequest) {
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        ExceptionResponse exceptionResponse = getExceptionResponse(errors.toString(), HttpStatus.BAD_REQUEST, webRequest);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
