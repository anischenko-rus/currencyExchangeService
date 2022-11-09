package ru.anischenko.currency.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestDtoJSON {

    private String currencyToConvert;

    private Integer amountOfCurrency;

    private String currencyToWhichWeWillTransfer;

}
