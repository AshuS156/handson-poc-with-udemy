package com.in28minutes.microservices.rate_limitor.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RateLimitorController{

    @GetMapping("/rateLimitor")
    @RateLimiter(name="default")
    public String rateLimitor(){
        System.out.println("Rate Limitor");
        return "Rate Limitor";
    }

}
