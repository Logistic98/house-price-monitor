package com.xxljob;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.xxljob.dao")
@SpringBootApplication
public class ExecutorApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ExecutorApplication.class, args);
        LOGGER.info("======项目启动成功！======");
    }

}
