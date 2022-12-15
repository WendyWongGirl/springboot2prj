package com.example.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Wang Ying
 * @date 2022.8.2
 * @description Swagger配置类
 */
@EnableWebMvc
@EnableSwagger2
@ComponentScan("com.example.controller")
@Configuration
public class SwaggerConfig {
    //原路径：http://localhost/swagger-ui.html
    //bootstrap路径：http://localhost/doc.html
    //配置swagger核心配置 docket，主要是添加要扫描的controller路径（如下的 withMethodAnnotation 可换成 basePackage）

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))                         //这里采用包含注解的方式来确定要显示的接口
//                .apis(any())    //这里采用包扫描的方式来确定要显示的接口
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("基于SpringBoot整合SSM案例") // 任意，请稍微规范点
                .description("接口API文档") // 任意，请稍微规范点
                .termsOfServiceUrl("http://localhost:8081/swagger-ui.html") // 将“url”换成自己的ip:port
//                .contact(new Contact("iter.com", "", ""))// 扩展（如：项目的别称）
                .version("1.1.0")
                .build();
    }

}
