package com.coralogix.calculator.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ExchangeRate {
    @Id
    @GeneratedValue
    private Integer id;
    private String originCurrency;
    private String value;
    private String date;
    private String finalCurrency;
}
