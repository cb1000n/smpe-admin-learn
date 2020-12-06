package com.zhang.config;

import com.google.common.base.Predicates;
import com.zhang.config.bean.SwaggerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @ClassName; SwaggerConfig
 * @description: TODO 类描述
 * @Author: ZhangRenjie
 * @date: 2020/12/3 20:03
 */
@RequiredArgsConstructor
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final SwaggerProperties swaggerProperties;

    @Bean
    @SuppressWarnings("all")
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerProperties.getEnabled())
                .apiInfo(apiInfo())
                .host(swaggerProperties.getSwaggerHost())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhang"))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                swaggerProperties.getTitle(),
                swaggerProperties.getDescription(),
                swaggerProperties.getVersion(),
                "http://www.marchsoft.cn/",
                new Contact("我想做阿信","https://blog.csdn.net/qq_42909053","1273206268@qq.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()

        );
    }
}