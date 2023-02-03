package com.example.investment.stockorder.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

  private ErrorMessage getStandardErrorMessage(Exception ex, WebRequest request, HttpStatus httpStatus) {
    log.error("Exception occurred: " + ex.getMessage());
    return ErrorMessage.builder()
        .statusCode(httpStatus.value())
        .timestamp(LocalDateTime.now())
        .message(ex.getMessage())
        .description(request.getDescription(false))
        .build();
  }
}
