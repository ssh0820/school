package com.zerobase.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //http://localhost:8081/swagger-ui/index.html
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zerobase.school"))
                .paths(PathSelectors.ant("/**"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("서울초등교사 커뮤니티")
                .description("서울 초등학교 교사들에게 서울시 전체의 초등학교 정보를 한눈에 볼수 있고 커뮤니티를 통해 소통하며 도움을 얻을 수 있는 플랫폼")
                .version("1.0")
                .build();
    }
}

