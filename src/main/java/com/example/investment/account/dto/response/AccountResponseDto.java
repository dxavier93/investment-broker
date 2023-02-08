package com.example.investment.account.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDto {
  private String number;
  private String clientName;
}
