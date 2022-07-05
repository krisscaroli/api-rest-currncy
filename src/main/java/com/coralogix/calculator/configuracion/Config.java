package com.coralogix.calculator.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean("apiCurrencyRest")
    public RestTemplate apiCurrencyRest(){
        return new RestTemplate() ;
    }

}
