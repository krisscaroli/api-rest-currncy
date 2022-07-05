package com.coralogix.calculator.repository;

import com.coralogix.calculator.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate,Integer> {
    ExchangeRate findExchangeRateByOriginCurrencyAndFinalCurrency(String originCurrency, String finalCurrency);
}
