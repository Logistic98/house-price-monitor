package com.house;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.house.dao")
@SpringBootApplication
public class AdminApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        LOGGER.info("======项目启动成功！======");
        LOGGER.info("Druid地址：http://localhost:8080/druid/index.html");
        LOGGER.info("Swagger地址--旧版：http://localhost:8080/swagger-ui.html");
        LOGGER.info("Swagger地址--新版：http://localhost:8080/doc.html");
    }

}
