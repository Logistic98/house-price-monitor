package com.house.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class TokenConfig {

    /**
     * 发行者
     */
    private String issuer;

    /**
     * 密钥
     */
    private String secretKey;

    /**
     * token 过期时间
     */
    private Duration accessTokenExpireTime;

    /**
     * pc端 刷新 token 过期时间
     */
    private Duration refreshTokenExpireTime;

    /**
     * app端 刷新 token 过期时间
     */
    private Duration refreshTokenExpireAppTime;
}
