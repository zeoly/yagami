package com.thinkequip.anchat.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author zengyongli
 */
@Service
public class HelloService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String hello() {
        long start = System.currentTimeMillis();
        String result = restTemplate.getForEntity("http://anochat-consumer-service/home", String.class).getBody();
        long end = System.currentTimeMillis();
        logger.info("spend:" + (end - start));
        return result;
    }

    public String helloFallback() {
        return "error";
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
