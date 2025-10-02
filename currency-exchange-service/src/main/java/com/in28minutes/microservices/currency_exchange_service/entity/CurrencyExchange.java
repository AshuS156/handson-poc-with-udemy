package com.in28minutes.microservices.currency_exchange_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="currency_exchange")
public class CurrencyExchange{

    @Id
    private Long id;

    @Column(name="from_currency")
    private String from;
    @Column(name="to_currency")
    private String to;
    private Double conversionMultiple;
    private String environment;

    public CurrencyExchange() {

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFromCurrency() {
        return from;
    }
    public void setFromCurrency(String from) {
        this.from = from;
    }
    public String getToCurrency() {
        return to;
    }
    public void setToCurrency(String to) {
        this.to = to;
    }


    public Double getConversionMultiple() {
        return conversionMultiple;
    }
    public void setConversionMultiple(Double conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }
    public String getEnvironment() {
        return environment;
    }
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
