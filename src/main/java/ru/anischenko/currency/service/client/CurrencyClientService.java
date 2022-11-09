package ru.anischenko.currency.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.anischenko.currency.dto.ResponseCurrencyServiceJSON;

@FeignClient(name = "${currency-api.name}", url = "${currency-api.base.url}")
public interface CurrencyClientService {

    @GetMapping("/historical/{date}.json?app_id={app_id}&base={base}")
    ResponseCurrencyServiceJSON getCurrency(@PathVariable("date") String date, @PathVariable("app_id") String app_id,@PathVariable("base") String base);
}
