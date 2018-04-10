package com.yahacode.yagami.document.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zengyongli 2018-04-08
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "yagami.document")
public class DocumentProperties {

    private String localStorage;
}
