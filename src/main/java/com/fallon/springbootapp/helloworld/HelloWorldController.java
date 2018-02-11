package com.fallon.springbootapp.helloworld;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String returnHelloWorld() {
        return "Hello World";
    }
}
