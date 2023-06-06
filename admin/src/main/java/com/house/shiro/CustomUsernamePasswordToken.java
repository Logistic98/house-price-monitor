package com.house.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Shiro 不支持 jwt-token，所以我们需要继承 UsernamePasswordToken ，把 jwt-token 设置到 Shiro 中
 */
public class CustomUsernamePasswordToken extends UsernamePasswordToken {

    private String token;

    public CustomUsernamePasswordToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}
