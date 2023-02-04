package com.vivek.practice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class PracticeRestController {

    // expose "/" endpoint that return "Hello-world"

    @GetMapping("/")
    public String sayHello(){
        return "Hello world!Time on server is "+ LocalDateTime.now();
    }
}
