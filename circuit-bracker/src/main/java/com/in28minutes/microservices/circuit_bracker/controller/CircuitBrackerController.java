package com.in28minutes.microservices.circuit_bracker.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBrackerController{

    @GetMapping("/sample-api")
    @CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodedResponse")
    public String sampleApi() {
        final ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",String.class);
        return forEntity.getBody();
    }
    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}
