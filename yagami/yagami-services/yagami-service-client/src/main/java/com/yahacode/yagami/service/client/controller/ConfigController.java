package com.yahacode.yagami.service.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengyongli 2018-05-24
 */
@RefreshScope
@RestController
public class ConfigController {

    @Value("${msg:test}")
    private String msg;

    @GetMapping("/msg")
    public String getMsg() {
        return msg;
    }
}
