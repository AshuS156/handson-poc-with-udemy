package com.in28minutes.microservices.currency_exchange_service.bean;

public class CurrencyExchangeRequest{
    private String from;
    private String to;
    private Double conversionMultiple;
    private String environment;

    public CurrencyExchangeRequest() {

    }

    public String getFrom(){
        return from;
    }

    public void setFrom(String from){
        this.from = from;
    }

    public String getTo(){
        return to;
    }

    public void setTo(String to){
        this.to = to;
    }

    public Double getConversionMultiple(){
        return conversionMultiple;
    }

    public void setConversionMultiple(Double conversionMultiple){
        this.conversionMultiple = conversionMultiple;
    }

    public String getEnvironment(){
        return environment;
    }

    public void setEnvironment(String environment){
        this.environment = environment;
    }
}
