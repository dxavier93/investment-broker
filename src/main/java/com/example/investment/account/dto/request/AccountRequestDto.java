package com.example.investment.account.dto.request;

import com.example.investment.account.model.Account;
import com.example.investment.order.dto.request.RequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountRequestDto implements RequestDto<Account> {
  @NotBlank
  private String number;
  @NotBlank
  private String clientName;

  @Override
  public Account toEntity() {
    return Account.builder()
        .number(number)
        .clientName(clientName)
        .build();
  }

  @Override
  public Account toEntity(Account account) {
    return Account.builder()
        .id(account.getId())
        .number(number)
        .clientName(clientName)
        .build();
  }
}
