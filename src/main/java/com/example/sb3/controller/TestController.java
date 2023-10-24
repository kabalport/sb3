package com.example.sb3.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/hello/world")
    public String helloWorld() {
        return "[Get] Hello, world!";
    }

    @PostMapping("/hello/world")
    public String postHelloWorld() {
        return "[Post] Hello, world";
    }

    @PutMapping("/hello/world")
    public String putHelloWorld() {
        return "[Put] Hello, world";
    }

    @DeleteMapping("/hello/world")
    public String deleteHelloWorld() {
        return "[Delete] Hello, world";
    }
}
