package com.yahacode.yagami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication(scanBasePackages = {"com.yahacode.yagami"})
@EnableTransactionManagement
//@EnableEurekaClient
@EnableSwagger2
@EnableJpaRepositories(basePackages = {"com.yahacode.yagami.**.repository"})
@EntityScan(basePackages = {"com.yahacode.yagami.**.model"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
        return new HibernateJpaSessionFactoryBean();
    }

}
