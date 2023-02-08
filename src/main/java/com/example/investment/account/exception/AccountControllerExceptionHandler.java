package com.example.investment.account.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class AccountControllerExceptionHandler {

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
