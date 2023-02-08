package com.example.investment.account.model;

import com.example.investment.account.dto.response.AccountResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TABLE_ACCOUNT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account implements StandardModel<AccountResponseDto> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String number;
  @NotNull
  private String clientName;

  @Override
  public AccountResponseDto toResponseDto() {
    return AccountResponseDto.builder()
        .number(number)
        .clientName(clientName)
        .build();
  }
}
