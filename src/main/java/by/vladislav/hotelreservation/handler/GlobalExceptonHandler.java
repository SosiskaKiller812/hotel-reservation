package by.vladislav.hotelreservation.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import by.vladislav.hotelreservation.exception.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptonHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String x(EntityNotFoundException exception) {
    return exception.getMessage();
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String x(RuntimeException exception) {
    return exception.getMessage();
  }

}
