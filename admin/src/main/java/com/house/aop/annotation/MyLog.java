package com.house.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解（记录日志）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {

    /**
     * 记录用户操作哪个模块
     */
    String title() default "";

    /**
     * 记录用户操作的动作
     */
    String action() default  "";
}
