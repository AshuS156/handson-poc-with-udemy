package com.in28minutes.microservices.limits_service.controller;

import com.in28minutes.microservices.limits_service.beans.LimitService;
import com.in28minutes.microservices.limits_service.config.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController{

    @Autowired
    LimitConfiguration configuration;

    @GetMapping("/limits")
    public LimitService retrieveLimits(){
        return new LimitService(configuration.getMaximum(), configuration.getMinimum());
    }
}
