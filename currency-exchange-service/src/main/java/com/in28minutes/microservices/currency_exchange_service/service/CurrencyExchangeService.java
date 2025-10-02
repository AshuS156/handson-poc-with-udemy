package com.in28minutes.microservices.currency_exchange_service.service;

import com.in28minutes.microservices.currency_exchange_service.bean.CurrencyExchangeRequest;
import com.in28minutes.microservices.currency_exchange_service.bean.CurrencyExchangeResponse;
import com.in28minutes.microservices.currency_exchange_service.entity.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService{

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    Environment environment;

    public CurrencyExchangeResponse findByFromAndTo(String from,String to){
        final CurrencyExchange currencyExchangeData = currencyExchangeRepository.findByFromAndTo(from,to);
        if (currencyExchangeData == null) {
            throw new RuntimeException("Unable to find data for " + from + " to " + to);
        }
        final CurrencyExchangeResponse currencyExchangeResponse =
                new CurrencyExchangeResponse(currencyExchangeData.getId(),currencyExchangeData.getFromCurrency(),currencyExchangeData.getToCurrency(),currencyExchangeData.getConversionMultiple());

        final String property = environment.getProperty("local.server.port");
        currencyExchangeResponse.setEnvironment(property);
        System.out.println("CurrencyExchangeResponse : " + currencyExchangeResponse);
        return currencyExchangeResponse;
    }

    public CurrencyExchangeResponse save(CurrencyExchangeRequest request){
        CurrencyExchange currencyExchange = new CurrencyExchange();
        currencyExchange.setFromCurrency(request.getFrom());
        currencyExchange.setToCurrency(request.getTo());
        currencyExchange.setConversionMultiple(request.getConversionMultiple());
        CurrencyExchange savedEntity = currencyExchangeRepository.save(currencyExchange);
        return new CurrencyExchangeResponse(savedEntity.getId(), savedEntity.getFromCurrency(), savedEntity.getToCurrency(), savedEntity.getConversionMultiple());
    }

    public CurrencyExchangeResponse deleteById(Long id){
        CurrencyExchange currencyExchange = currencyExchangeRepository.findById(id).orElse(null);
        if(currencyExchange != null){
            currencyExchangeRepository.deleteById(id);
            return new CurrencyExchangeResponse(currencyExchange.getId(), currencyExchange.getFromCurrency(), currencyExchange.getToCurrency(), currencyExchange.getConversionMultiple());
        } else {
            throw new RuntimeException("Unable to find data for id " + id);
        }
    }
}
