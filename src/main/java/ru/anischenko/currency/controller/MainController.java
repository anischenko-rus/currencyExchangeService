package ru.anischenko.currency.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anischenko.currency.dto.RequestDtoJSON;
import ru.anischenko.currency.dto.ResponseDtoJSON;
import ru.anischenko.currency.service.CurrencyExchangeService;

@RestController
public class MainController {

    private static final String GET_CURRENCY_THAT_WAS_EXCHANGED = "/api/v1/exchange";

    private final CurrencyExchangeService currencyExchangeService;

    public MainController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }


    @GetMapping(GET_CURRENCY_THAT_WAS_EXCHANGED)
    public ResponseEntity<ResponseDtoJSON> getCurrencyThatWasExchanged (@RequestParam String currencyToConvert,
                                                                        @RequestParam Integer amountOfCurrency,
                                                                        @RequestParam String currencyToWhichWeWillTransfer){
        return ResponseEntity.ok(currencyExchangeService.exchangeCurrency(RequestDtoJSON.builder()
                .currencyToConvert(currencyToConvert)
                .amountOfCurrency(amountOfCurrency)
                .currencyToWhichWeWillTransfer(currencyToWhichWeWillTransfer)
                .build()));
    }
}
