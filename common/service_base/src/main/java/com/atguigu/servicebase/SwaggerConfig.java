package com.atguigu.servicebase;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // swagger annotation
public class SwaggerConfig {
  @Bean
  public Docket webApiConfig(){
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("webApi")
        .apiInfo(webApiInfo())
        .select()
        .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
        .paths(Predicates.not(PathSelectors.regex("/error.*")))
        .build();

  }

  private ApiInfo webApiInfo(){

    return new ApiInfoBuilder()
        .title("Website - Course Center API Documentation")
        .description("This doc displays info of course center micro-service")
        .version("1.0")
        .contact(new Contact("java", "http://atguigu.com", "chuwang2015@126.com"))
        .build();
  }
}
