package com.sevenandhalf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionResponse> resourceNotFound(NotFoundException notFoundException) {
    ExceptionResponse response = new ExceptionResponse();
    response.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
    response.setStatus(HttpStatus.NOT_FOUND.value());
    response.setMessage(notFoundException.getMessage());
    response.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ExceptionResponse> resourceAlreadyExists(ConflictException conflictException) {
    ExceptionResponse response = new ExceptionResponse();
    response.setError(HttpStatus.CONFLICT.getReasonPhrase());
    response.setStatus(HttpStatus.CONFLICT.value());
    response.setMessage(conflictException.getMessage());
    response.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(UnAuthorizedException.class)
  public ResponseEntity<ExceptionResponse> unauthorizedException(UnAuthorizedException unAuthorizedException) {
    ExceptionResponse response = new ExceptionResponse();
    response.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setMessage(unAuthorizedException.getMessage());
    response.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ExceptionResponse> badRequestException(BadRequestException badRequestException) {
    ExceptionResponse response = new ExceptionResponse();
    response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    response.setMessage(badRequestException.getMessage());
    response.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<ExceptionResponse> forbiddenException(ForbiddenException forbiddenException) {
    ExceptionResponse response = new ExceptionResponse();
    response.setError(HttpStatus.FORBIDDEN.getReasonPhrase());
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setMessage(forbiddenException.getMessage());
    response.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> genericException(Exception exception) {
    ExceptionResponse response = new ExceptionResponse();
    response.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.setMessage(exception.getMessage());
    response.setTimestamp(LocalDateTime.now());

    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }


}
