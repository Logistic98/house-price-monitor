package com.house.config;

import com.house.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 添加 bean 到容器中
 */
@Configuration
public class BeanConfig {

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
