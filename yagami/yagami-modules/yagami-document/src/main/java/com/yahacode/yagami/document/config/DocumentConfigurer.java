package com.yahacode.yagami.document.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * download path proxy
 *
 * @author zengyongli
 */
@Configuration
public class DocumentConfigurer extends WebMvcConfigurationSupport {

    @Value("${local.storage")
    private String localStorage;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc/**").addResourceLocations("file:" + localStorage);
        super.addResourceHandlers(registry);
    }

}
