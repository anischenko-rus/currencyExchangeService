package ru.anischenko.currency.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseCurrencyServiceJSON {
    private String disclaimer;

    private String license;

    private Integer timestamp;

    private String base;

    private Map<String, Double> rates;
}
