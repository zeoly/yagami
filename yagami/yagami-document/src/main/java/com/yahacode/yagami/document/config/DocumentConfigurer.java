package com.yahacode.yagami.document.config;

import com.yahacode.yagami.document.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * download path proxy
 *
 * @author zengyongli
 */
@Configuration
public class DocumentConfigurer extends WebMvcConfigurerAdapter {

    private FileUtils fileUtils;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc/**").addResourceLocations("file:" + fileUtils.getLocalStorage());
        super.addResourceHandlers(registry);
    }

    @Autowired
    public void setFileUtils(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }
}
