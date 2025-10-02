package com.in28minutes.microservices.currency_conversion_service.controller;

import com.in28minutes.microservices.currency_conversion_service.bean.CurrencyConversion;
import com.in28minutes.microservices.currency_conversion_service.config.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
public class CurrencyConversionController{

    @Autowired
    CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversion> calculateCurrencyConversion(@PathVariable String from,@PathVariable String to,
                                                                          @PathVariable Double quantity){

        System.out.println("Inside currency conversion");
        HashMap<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        System.out.println(uriVariables);
        ResponseEntity<CurrencyConversion> responseEntity =
        new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,uriVariables);
        System.out.println("responseEntity.getBody() "+ responseEntity.getBody());

         CurrencyConversion currencyConversionResp = responseEntity.getBody();
        return ResponseEntity.ok(new CurrencyConversion(currencyConversionResp.getId(),currencyConversionResp.getFrom(),
                                                        currencyConversionResp.getTo(),
                                                        currencyConversionResp.getConversionMultiple(),quantity,
                                                     quantity * currencyConversionResp.getConversionMultiple(),
                                                        currencyConversionResp.getEnvironment()+" "+"rest template"));
    }


    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversion> calculateCurrencyConversionusingFeign(@PathVariable String from,@PathVariable String to,
                                                                          @PathVariable Double quantity){

        System.out.println("Inside currency conversion");

        CurrencyConversion currencyConversionResp = currencyExchangeProxy.retrieveExchangeValue(from,to);
        return ResponseEntity.ok(new CurrencyConversion(currencyConversionResp.getId(),currencyConversionResp.getFrom(),
                                                        currencyConversionResp.getTo(),
                                                        currencyConversionResp.getConversionMultiple(),quantity,
                                                        quantity * currencyConversionResp.getConversionMultiple(),
                                                        currencyConversionResp.getEnvironment()+" "+"Fiegn client"));
    }
}
