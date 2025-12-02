package com.projects.productserviceaug25.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {
    @GetMapping("/hello/{name}/{times}")
    public String sayHello(@PathVariable String name, @PathVariable int times) {
        String s = "";
        for (int i = 0; i < times; i++) {
            s+="Hello "+name+"!";
            s+=" <br> ";
        }
        return s;

    }
    @GetMapping("/hi")
    public String sayHi() {
        return "Hi there!";
    }

}
