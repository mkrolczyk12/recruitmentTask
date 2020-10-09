package io.github.recruitmentTask.recruitmentTask.common;

import com.fasterxml.jackson.core.JsonParseException;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RestControllerAdvice(annotations = GeneralExceptionsProcessing.class)
@Order(1)
public class GeneralExceptionsControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GeneralExceptionsControllerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        final String message = "One of the required values is missing";
        logger.warn("captured MethodArgumentNotValidException: " + message);
        ExceptionResponse resp = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), message, "-");
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, WebRequest request) {
        final String message = "JSON parse error: check the request syntax";
        logger.warn("captured HttpMessageNotReadableException: " + message);
        ExceptionResponse resp = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), message, "-");
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IOException.class)
    public final ResponseEntity<Object> handleIOException(IOException exception, WebRequest request) {
        final String message = "an internal error occurred while working with given data";
        logger.warn("captured IOException: " + message);
        ExceptionResponse resp = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), message, "-");
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(JsonParseException.class)
    public final ResponseEntity<Object> handleJsonParseException(JsonParseException exception, WebRequest request) {
        final String message = "error while parsing given JSON data";
        logger.warn("captured JsonParseException: " + message);
        ExceptionResponse resp = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), message, "-");
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest request) {
        final String message = exception.getMessage();
        logger.warn("captured NotFoundException: " + message);
        ExceptionResponse resp = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), message, "-");
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}
