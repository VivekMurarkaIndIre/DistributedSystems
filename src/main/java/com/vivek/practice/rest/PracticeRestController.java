package com.vivek.practice.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class PracticeRestController {
   //read from application.properties files
    @Value("${developer.name}")
    private String name;

    // expose "/" endpoint that return "Hello-world"
    //@GetMapping - shortcut for @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/")
    public String sayHello(){
        return "Hello world! My name is "+ name +". Time on server is "+ LocalDateTime.now();
    }

    @GetMapping("/dev")
    public String devTest(){
        return "My dev tools takes this endpoint without restarting the server";
    }

}
