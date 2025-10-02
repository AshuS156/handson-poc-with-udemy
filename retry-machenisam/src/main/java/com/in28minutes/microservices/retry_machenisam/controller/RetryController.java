package com.in28minutes.microservices.retry_machenisam.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RetryController{


    @GetMapping("/retry")
    @Retry(name = "retryService", fallbackMethod = "myRetryFallback")
    public String retry() {
        System.out.println("Retry method called");
        final ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",String.class);
        return forEntity.getBody();
    }

    public String myRetryFallback(Exception ex){
        return "Fallback method called after retries failed: " + ex.getMessage();
    }
}
