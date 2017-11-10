package com.thinkequip.anochat.consumer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @RequestMapping("/home")
    public String home() {
        System.out.println("consumer");
        return "consumer";
    }
}
