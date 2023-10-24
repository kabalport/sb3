package com.example.sb3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @GetMapping("/ping")
    public String helloWorld() {
        return "pong";
    }
    @GetMapping("/ping2")
    public String helloWorld2() {
        return "pong2";
    }
}
