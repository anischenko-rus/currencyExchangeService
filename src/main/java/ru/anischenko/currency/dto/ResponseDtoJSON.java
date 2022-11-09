package ru.anischenko.currency.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDtoJSON {

    private String amountAtTheExchangeRate;
}
