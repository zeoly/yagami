package com.yahacode.yagami.document.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author zengyongli
 */
@Component
@PropertySource(value = {"classpath:application.properties"})
public class DocumentConfig {

    @Value("${local.storage}")
    private String localStorage;

    public String getLocalStorage() {
        return localStorage;
    }

    public void setLocalStorage(String localStorage) {
        this.localStorage = localStorage;
    }

}
