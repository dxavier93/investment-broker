package com.example.investment.stockorder.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorMessage {
  private int statusCode;
  private LocalDateTime timestamp;
  private String message;
  private String description;
}
