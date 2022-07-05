package com.coralogix.calculator.controller;

import com.coralogix.calculator.entity.ExchangeRate;
import com.coralogix.calculator.services.ExchangeRateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchangeRate")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping
    public ExchangeRate getExchangeRate(@RequestParam String originCurrency, @RequestParam String finalCurrency){
        return exchangeRateService.getExchangeRate(originCurrency,finalCurrency);

    }


    @GetMapping("/all")
    public List<ExchangeRate> getAllExchangeRate(){
        return exchangeRateService.getAllExchangeRate();

    }

}
