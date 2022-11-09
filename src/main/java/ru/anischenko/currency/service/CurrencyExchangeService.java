package ru.anischenko.currency.service;

import ru.anischenko.currency.dto.RequestDtoJSON;
import ru.anischenko.currency.dto.ResponseDtoJSON;

public interface CurrencyExchangeService {
    ResponseDtoJSON exchangeCurrency(RequestDtoJSON requestDtoJSON);
}
