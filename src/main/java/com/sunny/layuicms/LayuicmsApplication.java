package com.sunny.layuicms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.sunny.layuicms.sys.mapper"})
public class LayuicmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LayuicmsApplication.class, args);
    }

}
