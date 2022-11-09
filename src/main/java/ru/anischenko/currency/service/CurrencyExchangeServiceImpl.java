package ru.anischenko.currency.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anischenko.currency.dto.RequestDtoJSON;
import ru.anischenko.currency.dto.ResponseCurrencyServiceJSON;
import ru.anischenko.currency.dto.ResponseDtoJSON;
import ru.anischenko.currency.exception.BadCurrencyException;
import ru.anischenko.currency.service.client.CurrencyClientService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService{

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final String ZONE_ID = "US/Eastern";
    
    private static final String BAD_REQUEST_MESSAGE = "This currency is not listed or does not exist";

    private final CurrencyClientService currencyClientService;

    public CurrencyExchangeServiceImpl(CurrencyClientService currencyClientService) {
        this.currencyClientService = currencyClientService;
    }

    @Value("${currency-api.app_id}")
    private String APP_ID;


    @Override
    public ResponseDtoJSON exchangeCurrency(RequestDtoJSON requestDtoJSON) {
        String dateNow = LocalDate.now(ZoneId.of(ZONE_ID)).format(DateTimeFormatter.ofPattern(DATE_PATTERN));
        ResponseCurrencyServiceJSON currency = currencyClientService.getCurrency(dateNow, APP_ID, requestDtoJSON.getCurrencyToConvert());
        String currencyToWhichWeWillTransfer = requestDtoJSON.getCurrencyToWhichWeWillTransfer();
        checkCurrency(currency,currencyToWhichWeWillTransfer);

        Double rateDoubleToConvert = currency.getRates().get(currencyToWhichWeWillTransfer);

        return ResponseDtoJSON
                .builder()
                .amountAtTheExchangeRate(String.valueOf(requestDtoJSON.getAmountOfCurrency() * rateDoubleToConvert))
                .build();
    }

    private static void checkCurrency(ResponseCurrencyServiceJSON response,String currency){
        if (!response.getRates().containsKey(currency)) {
            throw new BadCurrencyException(BAD_REQUEST_MESSAGE);
        }
    }
}
