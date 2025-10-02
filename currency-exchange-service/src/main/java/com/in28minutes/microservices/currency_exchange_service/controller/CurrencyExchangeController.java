package com.in28minutes.microservices.currency_exchange_service.controller;

import com.in28minutes.microservices.currency_exchange_service.bean.CurrencyExchangeRequest;
import com.in28minutes.microservices.currency_exchange_service.bean.CurrencyExchangeResponse;
import com.in28minutes.microservices.currency_exchange_service.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CurrencyExchangeController{

    @Autowired
    CurrencyExchangeService currencyExchangeService;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchangeResponse retrieveExchangeValue(@PathVariable  String from, @PathVariable String to){
        System.out.println("Inside currency exchange : from  : to " + from + " " + to);
        System.out.println(from);
        System.out.println(to);
        final CurrencyExchangeResponse currencyExchangeResponse = currencyExchangeService.findByFromAndTo(from,to);
        System.out.println("currencyExchangeResponse in controller : "+ currencyExchangeResponse);
        return currencyExchangeResponse;
    }

    @PostMapping("/save/currency-exchange")
    public ResponseEntity<CurrencyExchangeResponse> saveExchangeValue(@RequestBody CurrencyExchangeRequest requestPayload){
        return ResponseEntity.ok(currencyExchangeService.save(requestPayload));
    }

    @DeleteMapping("/delete/currency-exchange/{id}")
        public CurrencyExchangeResponse deleteExchangeValue(@PathVariable Long id){
        return currencyExchangeService.deleteById(id);
        }

}
