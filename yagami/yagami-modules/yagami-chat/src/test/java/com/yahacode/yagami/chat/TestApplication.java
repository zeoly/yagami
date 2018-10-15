package com.yahacode.yagami.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author zengyongli 2018-04-10
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.yahacode.yagami.**.repository"})
@EntityScan(basePackages = {"com.yahacode.yagami.**.model"})
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
