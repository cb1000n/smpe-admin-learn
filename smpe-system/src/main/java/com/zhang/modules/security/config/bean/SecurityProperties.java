package com.zhang.modules.security.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName SecurityProperties
 * Description TODO 类描述Jwt参数配置 读取yml中对jwt的配置，注入到以下属性当中
 *
 * @author ZhangRenjie
 * Date  2021/1/19 14:49
 */
@Data
@ConfigurationProperties(prefix = "jwt")
@Configuration
public class SecurityProperties {

    /**
     * Request Hadders : Authorization
     */
    private String header;

    /**
     * 令牌前缀，最后留个空格 Bearer
     */
    private String tokenStartWith;

    /**
     * 必须使用最少88位的Base64对该令牌进行编码
     */
    private String base64Secret;

    /**
     * 令牌过期时间，单位毫秒
     */
    private Long tokenValidityInSeconds;

    /**
     * 在线用户 key，根据 key 查新 redis 中在线用户的数据
     */
    private String onlineKey;

    /**
     * 验证码 key
     */
    private String codeKey;

    /**
     * token 续期检查
     */
    private Long detect;

    /**
     * 续期时间
     */
    private Long renew;

    /**
     * 重写get方法，加入空格，防止空格造成影响
     */
    public String getTokenStartWith() {
        return tokenStartWith + " ";
    }
}