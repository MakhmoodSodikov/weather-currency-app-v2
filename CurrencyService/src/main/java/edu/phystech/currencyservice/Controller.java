package edu.phystech.currencyservice;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    private final CurrencyService currencyService;

    public Controller(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency")
    public List<Double> getCurrency(@RequestParam @Nullable String days) {
        int daysNum = days == null ? 1 : Integer.parseInt(days);
        return currencyService.getDollarCurrency(daysNum);
    }
}
