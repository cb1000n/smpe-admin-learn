package com.zhang.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName; SwaggerProperties
 * @description: TODO Swagger配置，从yml读取
 * @Author: ZhangRenjie
 * @date: 2020/12/4 20:00
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    /** 是否启用swagger */
    private Boolean enabled;

    /** 描述 */
    private String description;

    /** 标题 */
    private String title;

    /** 版本 */
    private String version;

    /** ip和host */
    private String swaggerHost;
}