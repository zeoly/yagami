package com.yahacode.yagami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import com.yahacode.yagami.base.impl.BaseDaoImpl;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.yahacode.yagami")
@EnableAutoConfiguration
@EntityScan(basePackages = "com.yahacode.yagami")
@EnableJpaRepositories(repositoryBaseClass = BaseDaoImpl.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean(name = "sessionFactory")
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}

}
