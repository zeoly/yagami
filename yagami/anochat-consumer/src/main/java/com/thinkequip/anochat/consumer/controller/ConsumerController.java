package com.thinkequip.anochat.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ConsumerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/home")
    public String home() throws InterruptedException {
        int sleepTime = new Random().nextInt(3000);
        logger.info("sleep time:" + sleepTime);
        Thread.sleep(sleepTime);
        return "hello";
    }
}
