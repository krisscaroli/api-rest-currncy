package com.coralogix.calculator.services;

import com.coralogix.calculator.configuracion.Config;
import com.coralogix.calculator.entity.ExchangeRate;
import com.coralogix.calculator.model.Resultado;
import com.coralogix.calculator.repository.ExchangeRateRepository;
import com.sun.net.httpserver.Headers;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;

    private final RestTemplate apiCurrencyRest;

    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository, RestTemplate apiCurrencyRest) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.apiCurrencyRest = apiCurrencyRest;
    }


    public ExchangeRate getExchangeRate(String originCurrency, String finalCurrency){
        Resultado resultado = apiCurrencyRest.getForObject
                ("http://localhost:8089/fixer/latest?base={origin}&symbols{final}",Resultado.class,
                        originCurrency,finalCurrency);
        Map<String, String> rates=resultado.getRates();
        String valor=rates.get(finalCurrency);
        log.info("rate {} ",valor );
        ExchangeRate exchangeRate=exchangeRateRepository.findExchangeRateByOriginCurrencyAndFinalCurrency
                (originCurrency,finalCurrency);
        
        if(exchangeRate==null){
            exchangeRate= new ExchangeRate();
            exchangeRate.setOriginCurrency(originCurrency);
            exchangeRate.setFinalCurrency(finalCurrency);
            exchangeRate.setDate(resultado.getDate());
            exchangeRate.setValue(valor);
            ExchangeRate exchangeRateNew=exchangeRateRepository.save(exchangeRate);
            return exchangeRateNew;
        }

        return exchangeRate;
    }

    public List<ExchangeRate> getAllExchangeRate() {
        return exchangeRateRepository.findAll();

    }
}
