package com.house.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jwt token 工具类
 */
public class JwtTokenUtil {
    /**
     * 实例
     */
    private static JwtTokenUtil instance;

    /**
     * 发行者
     */
    private String issuer;

    /**
     * 主体（subject 代表这个JWT的主体，即它的所有人 一般是用户id）
     */
    private String subObject;

    /**
     * 过期时间，默认7天
     */
    private long expired = 1000 * 60 * 60 * 24 * 7;

    /**
     * jwt构造
     */
    private static JwtBuilder jwtBuilder;

    /**
     * 密钥
     */
    private String secret;// 密钥

    /**
     * 获取实例
     * @return
     */
    public static JwtTokenUtil getInstance(){
        if (instance == null){
            instance = new JwtTokenUtil();
        }
        jwtBuilder = Jwts.builder();
        return instance;
    }

    /**
     * 荷载信息(通常是一个User信息，还包括一些其他的元数据)
     * @param key
     * @param val
     * @return
     */
    public JwtTokenUtil setClaim(String key, Object val){
        jwtBuilder.claim(key,val);
        return this;
    }

    /**
     * 生成 jwt token
     * @return
     */
    public String generateToken(){
        String token = jwtBuilder
                .setIssuer(issuer)// 发行者
                .setSubject(subObject) // 主体
                //.claim("id","121") // 参数
                .setIssuedAt(new Date()) // 发行时间
                .setExpiration(new Date(System.currentTimeMillis() + expired))
                .signWith(SignatureAlgorithm.HS256,secret) // 签名类型 与 密钥
                .compressWith(CompressionCodecs.DEFLATE)// 对载荷进行压缩
                .compact(); // 压缩一下
        return token;
    }

    /**
     * 解析 token
     * @param token
     * @return
     */
    public Claims parseToken(String token){
        try{
            final Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        }catch (Exception e){}
        return null;
    }

    /**
     * 验证是否过期
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token){
        try{
            Claims claims = parseToken(token);
            return claims != null && !claims.getExpiration().before(new Date());
        }catch (Exception e){
            return true;
        }
    }

    /**
     * 验证token是否验证通过
     * @param token
     * @return
     */
    public boolean checkToken(String token){
        try{
            return isTokenExpired(token);
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 获取用户id
     * @param token
     * @return
     */
    public String getUserId(String token){
        try{
            Claims claims = parseToken(token);
            return claims != null ? claims.getSubject() : null;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取token的剩余过期时间
     * @param token
     * @return
     */
    public long getRemainingTime(String token){
        long time = 0;
        try{
            Claims claims = parseToken(token);
            time = claims.getExpiration().getTime() - System.currentTimeMillis();
        }catch (Exception e){}
        return time;
    }

    /**
     * 设置发行者
     * @param issuer
     * @return
     */
    public JwtTokenUtil setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    /**
     * 设置主题
     * @param subObject
     * @return
     */
    public JwtTokenUtil setSubObject(String subObject) {
        this.subObject = subObject;
        return this;
    }

    public long getExpired() {
        return expired;
    }

    /**
     * 设置过期时间
     * @param expired
     * @return
     */
    public JwtTokenUtil setExpired(long expired) {
        this.expired = expired;
        return this;
    }

    /**
     * 设置密钥
     * @param secret
     * @return
     */
    public JwtTokenUtil setSecret(String secret) {
        this.secret = secret;
        return this;
    }
}
