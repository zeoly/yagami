package com.yahacode.yagami.service.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zengyongli 2018-05-25
 */
@RestController
public class SleuthController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/sleuth")
    public String sleuth() {
        logger.info("sleuth client 1");
        return restTemplate.getForEntity("http://client-server-2/sleuth", String.class).getBody();
    }
}
