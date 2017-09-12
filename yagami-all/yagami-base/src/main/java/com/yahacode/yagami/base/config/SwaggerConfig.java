package com.yahacode.yagami.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.yahacode.yagami")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring Boot中使用Swagger2构建RESTful APIs")
				.description("更多Spring Boot相关文章请关注：http://www.yahacode.com/")
				.termsOfServiceUrl("http://localhost:8081")
				.contact(new Contact("zeoly", "http://www.yahacode.com/", "email@email.com")).version("1.0").build();
	}
}
